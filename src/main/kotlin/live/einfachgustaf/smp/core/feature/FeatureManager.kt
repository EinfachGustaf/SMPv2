package live.einfachgustaf.smp.core.feature

/**
 * Manages the registration, activation, and deactivation of all system features.
 *
 * @property context The context used to initialize and manage each feature.
 */
class FeatureManager(private val context: FeatureContext) {

    /**
     * A list containing all registered features.
     * Features are added via the [register] method and handled collectively.
     */
    private val features = mutableListOf<Feature>()

    /**
     * Registers a new feature to be managed.
     *
     * @param feature The feature to register.
     */
    fun register(feature: Feature) {
        features += feature
    }

    /**
     * Enables all registered features using the provided context.
     * Logs successful activations and errors during the process.
     */
    fun enableAll() {
        features.forEach {
            try {
                it.enable(context)
                context.plugin.logger.info("Enabled feature: ${it.name}")
            } catch (e: Exception) {
                context.plugin.logger.severe("Failed to enable feature '${it.name}': ${e.message}")
                e.printStackTrace()
            }
        }
    }

    /**
     * Disables all registered features.
     * Logs successful deactivations and handles any errors gracefully.
     */
    fun disableAll() {
        features.forEach {
            try {
                it.disable()
                context.plugin.logger.info("Disabled feature: ${it.name}")
            } catch (e: Exception) {
                context.plugin.logger.severe("Failed to disable feature '${it.name}': ${e.message}")
            }
        }
    }
}

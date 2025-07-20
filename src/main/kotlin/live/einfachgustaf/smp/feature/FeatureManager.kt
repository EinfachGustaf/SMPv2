package live.einfachgustaf.smp.feature

class FeatureManager(private val context: FeatureContext) {

    private val features = mutableListOf<Feature>()

    fun register(feature: Feature) {
        features += feature
    }

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

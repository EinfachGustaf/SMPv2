package live.einfachgustaf.smp.core.feature

/**
 * Represents a feature that can be enabled or disabled within the system.
 * Each feature has a unique name and lifecycle methods for activation and deactivation.
 */
interface Feature {

    /**
     * The unique name of the feature.
     * Used for identification and registration within the system.
     */
    val name: String

    /**
     * Enables the feature using the given context.
     *
     * @param context The context that provides necessary information
     *                and configuration for initializing the feature.
     */
    fun enable(context: FeatureContext)

    /**
     * Disables the feature and releases any allocated resources.
     * Typically called during system shutdown or cleanup.
     */
    fun disable()
}

package live.einfachgustaf.smp.core.feature

interface Feature {
    val name: String
    fun enable(context: FeatureContext)
    fun disable()
}
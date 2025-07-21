package live.einfachgustaf.smp.feature

interface Feature {
    val name: String
    fun enable(context: FeatureContext)
    fun disable()
}
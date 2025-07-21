package live.einfachgustaf.smp.features.advancements

import live.einfachgustaf.smp.core.feature.Feature
import live.einfachgustaf.smp.core.feature.FeatureContext

class AdvancementsFeature(override val name: String = "Advancements"): Feature {

    override fun enable(context: FeatureContext) = Unit

    override fun disable() = Unit
}
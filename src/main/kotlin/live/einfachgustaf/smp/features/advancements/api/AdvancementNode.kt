package live.einfachgustaf.smp.features.advancements.api

data class AdvancementNode(
    val advancement: AbstractAdvancement,
    val x: Double,
    val y: Double,
    val children: List<AdvancementNode> = emptyList()
)
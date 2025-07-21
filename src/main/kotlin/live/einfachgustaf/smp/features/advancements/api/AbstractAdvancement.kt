package live.einfachgustaf.smp.features.advancements.api

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.minecraft.advancements.AdvancementType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

abstract class AbstractAdvancement(
    val title: String,
    val displayIcon: ItemStack,
    val description: TextComponent? = Component.empty(),
    val type: AdvancementType,
    val unlocks: List<AbstractAdvancement> = listOf()
) {
    abstract fun construct(context: AdvancementContext)

    open fun reward(player: Player) = Unit

    fun complete(player: Player) {
        // TODO
    }
}
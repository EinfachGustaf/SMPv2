package live.einfachgustaf.smp.features.advancements.impl

import live.einfachgustaf.smp.features.advancements.api.AbstractAdvancement
import live.einfachgustaf.smp.features.advancements.api.AdvancementContext
import net.minecraft.advancements.AdvancementType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class TestAdvancement: AbstractAdvancement(
    title = "Test Advancement",
    displayIcon = ItemStack(Material.STRING),
    description = null,
    type = AdvancementType.GOAL
) {

    override fun construct(context: AdvancementContext) {
        context.pluginManager.registerEvents(
            object : org.bukkit.event.Listener {
                @EventHandler
                fun handle(event: PlayerJoinEvent) {
                    // Handle player join event for this advancement
                    // For example, give the player the advancement
                    complete(event.player)
                }
            },
            context.plugin
        )
    }

    override fun reward(player: Player) {
        player.inventory.addItem(ItemStack(Material.DIAMOND)) // Reward player with a diamond
    }
}
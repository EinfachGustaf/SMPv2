package live.einfachgustaf.smp.features

import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import live.einfachgustaf.smp.core.feature.Feature
import live.einfachgustaf.smp.core.feature.FeatureContext
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class SpawnFeature(override val name: String = "Spawn") : Feature {

    @Suppress("UnstableApiUsage")
    override fun enable(context: FeatureContext) {
        context.plugin.lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            val commands = event.registrar()

            commands.register(
                Commands.literal("spawn")
                    .requires { it.sender is Player }
                    .executes { ctx ->
                        val player = ctx.source.sender as Player
                        val loc = Bukkit.getWorlds().first().spawnLocation
                        player.teleportAsync(loc)
                        player.sendMessage(Component.text("Du wurdest zum Spawn teleportiert!")) // TODO: better message + sound
                        return@executes 1
                    }.build()
            )
        }
    }

    override fun disable() = Unit
}
package live.einfachgustaf.smp.features

import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import live.einfachgustaf.smp.core.feature.Feature
import live.einfachgustaf.smp.core.feature.FeatureContext
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location

class SpawnFeature(override val name: String = "Spawn") : Feature {

    @Suppress("UnstableApiUsage")
    override fun enable(context: FeatureContext) {
        context.plugin.lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) {
            val commands = it.registrar()

            commands.register(
                Commands.literal("spawn")
                    .executes { ctx ->
                        val sender = ctx.source.sender
                        if (sender is org.bukkit.entity.Player) {
                            val loc = getSpawnLocation()
                            sender.teleportAsync(loc)
                            sender.sendMessage(Component.text("Du wurdest zum Spawn teleportiert!")) // TODO: better message + sound
                            return@executes 1
                        }
                        sender.sendMessage(Component.text("Nur Spieler können diesen Befehl nutzen.")) // TODO: better message + sound
                        0
                    }.build()
            )
        }
    }

    override fun disable() {
    }

    private fun getSpawnLocation(): Location {
        val world = Bukkit.getWorlds().firstOrNull() ?: throw IllegalStateException("Keine Welt geladen")
        return world.spawnLocation
    }
}
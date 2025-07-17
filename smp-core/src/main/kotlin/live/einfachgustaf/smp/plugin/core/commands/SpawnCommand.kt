package live.einfachgustaf.smp.plugin.core.commands

import io.papermc.paper.command.brigadier.BasicCommand
import io.papermc.paper.command.brigadier.CommandSourceStack
import live.einfachgustaf.smp.plugin.core.SMPCore
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Suppress("UnstableApiUsage")
class SpawnCommand: BasicCommand {

    override fun execute(
        commandSourceStack: CommandSourceStack,
        args: Array<out String>
    ) {

        val player: Player = commandSourceStack.sender as Player
        val spawnLocation = SMPCore.spawnLocation

        player.teleport(
            Location(
                spawnLocation.world,
                spawnLocation.x,
                (spawnLocation.world.getHighestBlockAt(spawnLocation.blockX, spawnLocation.blockZ).y + 1).toDouble(),
                spawnLocation.z,
                spawnLocation.yaw,
                spawnLocation.pitch
            )
        )
        player.sendMessage(
            SMPCore.miniMessage.deserialize(
                "<green>Du wurdest zum Spawn teleportiert!</green>"
            )
        )
    }

    override fun canUse(sender: CommandSender): Boolean {
        return sender is Player
    }
}
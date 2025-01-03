package live.einfachgustaf.mods.smp

import kotlinx.coroutines.launch
import live.einfachgustaf.mods.smp.advancement.AdvancementRegistry
import live.einfachgustaf.mods.smp.advancement.Advancements
import live.einfachgustaf.mods.smp.data.db.MongoDB
import live.einfachgustaf.mods.smp.discord.DiscordBot
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.silkmc.silk.core.annotations.ExperimentalSilkApi
import net.silkmc.silk.core.event.Events
import net.silkmc.silk.core.event.Server
import net.silkmc.silk.core.task.mcCoroutineScope
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * The logger for the SMP mod.
 */
val LOGGER: Logger = LogManager.getLogger("smp")

/**
 * The main entry point for the SMP mod.
 */
fun initMain() = Unit

/**
 * The entry point for the SMP mod.
 */
fun initServer() {
    // DATABASE
    MongoDB

    // EVENTS
    ServerPlayConnectionEvents.JOIN.register { player, _, _ ->
        Advancements.createAdvancements(player.player)
    }

    // Listener
    postStart()
}

/**
 * The post-start listener for the SMP mod.
 */
@OptIn(ExperimentalSilkApi::class)
fun postStart() = Events.Server.postStart.listen {
    // ADVANCEMENTS
    AdvancementRegistry

    // DISCORD BOT
    mcCoroutineScope.launch {
        DiscordBot(System.getenv("DISCORD_BOT_TOKEN")).boot()
    }
}
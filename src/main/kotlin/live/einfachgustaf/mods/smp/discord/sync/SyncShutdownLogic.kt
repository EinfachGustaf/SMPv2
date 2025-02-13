package live.einfachgustaf.mods.smp.discord.sync

import dev.kord.common.Color
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.entity.channel.TextChannel
import kotlinx.coroutines.launch
import net.silkmc.silk.core.annotations.ExperimentalSilkApi
import net.silkmc.silk.core.event.ServerEvents
import net.silkmc.silk.core.task.mcCoroutineScope

/**
 * Represents the shutdown logic for the chat sync.
 * @param parent The parent [ChatSync] instance.
 */
class SyncShutdownLogic(private val parent: ChatSync) {

    /**
     * Hooks the shutdown logic.
     * @param kord The [Kord] instance.
     */
    @OptIn(ExperimentalSilkApi::class)
    fun hook(kord: Kord) {
        ServerEvents.preStop.listen {
            mcCoroutineScope.launch {
                (kord.getChannel(parent.channelSnowflake) as TextChannel).createEmbed {
                    title = "Der Server wird heruntergefahren!"
                    description = "Der Server wird in Kürze heruntergefahren. Bitte verabschiedet euch! :wave: Wir sind bald wieder da!"
                    footer {
                        text = "EinfachGustaf SMP | Warte auf den Neustart!"
                    }
                    color = Color(235, 32, 14)
                }
            }
        }
    }
}
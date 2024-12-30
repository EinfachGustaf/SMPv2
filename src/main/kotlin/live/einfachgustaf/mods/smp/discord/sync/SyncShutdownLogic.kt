package live.einfachgustaf.mods.smp.discord.sync

import dev.kord.common.Color
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.entity.channel.TextChannel
import kotlinx.coroutines.launch
import live.einfachgustaf.mods.smp.discord.extensions.einfachGustafIcon
import net.silkmc.silk.core.annotations.ExperimentalSilkApi
import net.silkmc.silk.core.event.ServerEvents
import net.silkmc.silk.core.task.mcCoroutineScope

class SyncShutdownLogic(private val parent: ChatSync) {

    @OptIn(ExperimentalSilkApi::class)
    fun hook(kord: Kord) {
        ServerEvents.preStop.listen {
            mcCoroutineScope.launch {
                (kord.getChannel(parent.channelSnowflake) as TextChannel).createEmbed {
                    title = "Der Server wird heruntergefahren!"
                    description = "Der Server wird in Kürze heruntergefahren. Bitte verabschiedet euch! :wave: Wir sind bald wieder da!"
                    footer {
                        text = "EinfachGustaf SMP | Warte auf den Neustart!"
                        einfachGustafIcon()
                    }
                    color = Color(235, 32, 14)
                }
            }
        }
    }
}
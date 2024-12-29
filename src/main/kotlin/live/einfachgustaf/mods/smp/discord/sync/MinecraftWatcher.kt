package live.einfachgustaf.mods.smp.discord.sync

import dev.kord.core.Kord
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.channel.TextChannel
import kotlinx.coroutines.launch
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents
import net.silkmc.silk.core.task.mcCoroutineScope
import net.silkmc.silk.core.text.literalText

class MinecraftWatcher(val parent: ChatSync) {

    companion object {
        var lastName = ""; internal set
    }

    fun hook(kord: Kord) {
        // TODO: forbidden pings like (everyone, here, roles)
        ServerMessageEvents.CHAT_MESSAGE.register { message, player, _ ->
            if (message.signedContent().contains("@")) {
                player.sendSystemMessage(literalText("You are not allowed to mention Discord users!") {
                    color = 0xFF0000
                })
                return@register
            }
            mcCoroutineScope.launch {
                (kord.getChannel(parent.channelSnowflake) as TextChannel).createMessage {
                    content = buildString {
                        if (lastName != player.name.string) {
                            append("**${player.name.string}**:\n")
                        }
                        append("> ${message.signedContent()}")
                    }
                    lastName = player.name.string
                }
            }
        }
    }
}
package live.einfachgustaf.mods.smp.discord.sync

import dev.kord.core.Kord
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.text.broadcastText
import net.silkmc.silk.core.text.literalText

class DiscordWatcher(val parent: ChatSync) {

    fun hook(kord: Kord) {
        // TODO: ping player (@mc:playername)
        kord.on<MessageCreateEvent> {
            if (message.channelId != parent.channelSnowflake)
                return@on
            if (message.author?.isBot == true)
                return@on

            MinecraftWatcher.lastName = ""

            Silk.server?.broadcastText(literalText {
                text("[Discord] ")
                text(member?.username ?: "Unknown")
                text(": ")
                text(message.content)
            })

            message.addReaction(ReactionEmoji.Unicode("🟢"))
        }
    }
}
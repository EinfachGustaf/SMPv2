package live.einfachgustaf.mods.smp.discord.sync

import dev.kord.core.Kord
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import live.einfachgustaf.mods.smp.extensions.sendNotifcation
import net.minecraft.advancements.AdvancementType
import net.minecraft.world.item.Items
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

            var buildMessage = message.content
            val mcPrefix = "@mc:"
            val regex = Regex("$mcPrefix\\w+")
            val matches = regex.findAll(buildMessage)

            if (matches.any())
                matches.forEach { match ->
                    val playerName = match.value.removePrefix(mcPrefix)

                    val query = Silk.server?.playerList?.getPlayerByName(playerName)

                    if (query != null) {
                        buildMessage = buildMessage.replace(match.value, playerName)
                        query.sendNotifcation(literalText("Du wurdest von ${member?.username ?: "Unknown"} in Discord erwähnt!") {  }, icon = Items.RED_DYE.defaultInstance, type = AdvancementType.GOAL)
                    }
                }

            Silk.server?.broadcastText(literalText {
                text("[Discord] ")
                text(member?.username ?: "Unknown")
                text(": ")
                text(buildMessage)
            })

            message.addReaction(ReactionEmoji.Unicode("🟢"))
        }
    }
}
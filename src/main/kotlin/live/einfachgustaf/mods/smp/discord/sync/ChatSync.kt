package live.einfachgustaf.mods.smp.discord.sync

import dev.kord.common.Color
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.entity.channel.TextChannel
import live.einfachgustaf.mods.smp.discord.extensions.einfachGustafIcon

class ChatSync {

    val channelSnowflake = Snowflake(System.getenv("DISCORD_CHAT_SYNC_CHANNEL"))
    val shutdownLogic = SyncShutdownLogic(this)
    val discordWatcher = DiscordWatcher(this)
    val minecraftWatcher = MinecraftWatcher(this)

    suspend fun initialize(kord: Kord) {
        val channel = kord.getChannel(channelSnowflake) ?: error("Channel ${channelSnowflake.value} not found")

        if (channel !is TextChannel)
            error("Channel ${channelSnowflake.value} is not a text channel")

        shutdownLogic.hook(kord)
        discordWatcher.hook(kord)
        minecraftWatcher.hook(kord)

        // send message
        channel.createEmbed {
            title = "Hallo, Discord Welt! :wave:"
            description = "Der SMP Server hat sich erfolgreich mit dem Discord Channel verbunden! Ihr könnt ab jetzt mit den Leuten auf dem Minecraft Server hier kommunizieren."
            footer {
                text = "EinfachGustaf SMP | Sende einfach eine Nachricht im Chat!"
                einfachGustafIcon()
            }
            color = Color(84, 245, 66)
        }
    }
}
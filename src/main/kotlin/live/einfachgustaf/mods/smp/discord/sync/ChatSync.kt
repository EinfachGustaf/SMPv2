package live.einfachgustaf.mods.smp.discord.sync

import dev.kord.common.Color
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.entity.channel.TextChannel

/**
 * Represents the chat synchronization.
 */
class ChatSync {

    /**
     * The channel snowflake.
     */
    val channelSnowflake = Snowflake(System.getenv("DISCORD_CHAT_SYNC_CHANNEL"))

    /**
     * The [SyncShutdownLogic] instance.
     */
    val shutdownLogic = SyncShutdownLogic(this)

    /**
     * The [DiscordWatcher] instance.
     */
    val discordWatcher = DiscordWatcher(this)

    /**
     * The [MinecraftWatcher] instance.
     */
    val minecraftWatcher = MinecraftWatcher(this)

    /**
     * Initializes the chat synchronization.
     * @param kord The [Kord] instance.
     */
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
            }
            color = Color(84, 245, 66)
        }
    }
}
package live.einfachgustaf.mods.smp.discord

import dev.kord.core.Kord
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import live.einfachgustaf.mods.smp.LOGGER
import live.einfachgustaf.mods.smp.discord.sync.ChatSync

/**
 * Represents the Discord bot.
 * @param token The bot token.
 */
class DiscordBot(private val token: String) {

    /**
     * The [Kord] instance.
     */
    lateinit var kord: Kord

    /**
     * The [ChatSync] instance.
     */
    val chatSync = ChatSync()

    /**
     * Boots the Discord bot.
     */
    suspend fun boot() {
        LOGGER.info("Booting Discord bot...")
        try {
            kord = Kord(token)
        } catch (e: Exception) {
            LOGGER.error("Failed to boot Discord bot: ${e.message}")
            return
        }

        chatSync.initialize(kord)

        kord.login {
            @OptIn(PrivilegedIntent::class)
            intents += Intent.MessageContent
        }
    }
}
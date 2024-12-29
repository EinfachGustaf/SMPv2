package live.einfachgustaf.mods.smp.discord

import dev.kord.core.Kord
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import dev.kordex.core.ExtensibleBot
import live.einfachgustaf.mods.smp.LOGGER
import live.einfachgustaf.mods.smp.discord.sync.ChatSync
import live.einfachgustaf.mods.smp.discord.whitelist.WhitelistSetupCommand

class DiscordBot(private val token: String) {
    lateinit var bot: ExtensibleBot
    lateinit var kord: Kord
    val chatSync = ChatSync()

    @OptIn(PrivilegedIntent::class)
    suspend fun boot() {
        LOGGER.info("Booting Discord bot...")
        try {
            bot = ExtensibleBot(token) {
                extensions {
                    add(::WhitelistSetupCommand)
                }
                intents {
                    + Intent.MessageContent
                }
            }
            kord = bot.kordRef
        } catch (e: Exception) {
            LOGGER.error("Failed to boot Discord bot: ${e.message}")
            return
        }

        chatSync.initialize(kord)

        bot.start()
    }
}
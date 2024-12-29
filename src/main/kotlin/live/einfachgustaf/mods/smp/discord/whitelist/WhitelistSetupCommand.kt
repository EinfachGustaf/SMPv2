package live.einfachgustaf.mods.smp.discord.whitelist

import dev.kord.common.Color
import dev.kord.common.entity.Permission
import dev.kord.rest.builder.message.embed
import dev.kordex.core.components.components
import dev.kordex.core.components.publicButton
import dev.kordex.core.extensions.Extension
import dev.kordex.core.extensions.publicSlashCommand
import dev.kordex.core.i18n.toKey
import live.einfachgustaf.mods.smp.LOGGER

class WhitelistSetupCommand(override val name: String = "whitelistsetupcommand") : Extension() {

    override suspend fun setup() {

        publicSlashCommand {
            name = "whitelistsetup".toKey()
            description = "Richte die SMP Whitelist ein!".toKey()
            requirePermission(Permission.Administrator)

            // TODO: fix
            action {
                respond {
                    components {
                        publicButton(::WhitelistModal) {
                            label = "Whitelist".toKey()
                            embed {
                                title = "Whitelist"
                                description = "Klicke auf den Button, um dich zu whitelisten!"
                                color = Color(84, 245, 66)
                                footer {
                                    text = "EinfachGustaf SMP | Whitelist"
                                }
                            }
                            action {
                                LOGGER.warn("WhitelistModal action not implemented!")
                            }
                        }
                    }
                }
            }
        }
    }
}
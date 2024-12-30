package live.einfachgustaf.mods.smp.discord.whitelist

import dev.kord.common.Color
import dev.kord.common.entity.Permission
import dev.kord.rest.builder.message.embed
import dev.kordex.core.commands.Arguments
import dev.kordex.core.commands.application.slash.ephemeralSubCommand
import dev.kordex.core.commands.converters.impl.defaultingInt
import dev.kordex.core.commands.converters.impl.int
import dev.kordex.core.commands.converters.impl.string
import dev.kordex.core.extensions.Extension
import dev.kordex.core.extensions.ephemeralSlashCommand
import dev.kordex.core.i18n.toKey

class WhitelistKeyCommand(override val name: String = "whitelistkeycommand"): Extension() {

    override suspend fun setup() {
        ephemeralSlashCommand {
            name = "key".toKey()
            description = "Create new whitelist keys".toKey()
            requirePermission(Permission.Administrator)

            ephemeralSubCommand(::CreateArgs) {
                name = "create".toKey()
                description = "Create new whitelist keys".toKey()

                action {
                    val createManyKeys = DiscordWhitelist.createManyKeys(arguments.amount)

                    respond {
                        embed {
                            title = "Whitelist Key"
                            description = "Created ${createManyKeys.size} keys\n${createManyKeys.joinToString("\n") { "- ||`${it.key}`||" }}"
                            color = Color(84, 245, 66)
                            footer {
                                text = "EinfachGustaf SMP | Whitelist"
                            }
                        }
                    }
                }
            }

            ephemeralSubCommand(::DeleteArgs) {
                name = "delete".toKey()
                description = "Delete a whitelist key".toKey()

                action {
                    val deleteKey = DiscordWhitelist.deleteKey(arguments.key)

                    respond {
                        embed {
                            title = "Whitelist Key"
                            description = "" // set so command can be registered properly
                            color = Color(84, 245, 66)

                            if (deleteKey) {
                                description = "Deleted key ||`${arguments.key}`||"
                                color = Color(84, 245, 66)
                            } else {
                                description = "Error. Maybe Key ||`${arguments.key}`|| not found"
                                color = Color(245, 66, 66)
                            }
                        }
                    }
                }
            }
        }
    }

    inner class CreateArgs: Arguments() {

        val amount by defaultingInt {
            name = "amount".toKey()
            defaultValue = 1
            description = "The amount of keys to create".toKey()
        }
    }

    inner class DeleteArgs: Arguments() {

        val key by string {
            name = "key".toKey()
            description = "The key to delete".toKey()
        }
    }
}
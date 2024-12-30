package live.einfachgustaf.mods.smp.discord.whitelist

import dev.kord.common.Color
import dev.kord.common.entity.Permission
import dev.kord.rest.builder.message.embed
import dev.kordex.core.components.components
import dev.kordex.core.components.publicButton
import dev.kordex.core.extensions.Extension
import dev.kordex.core.extensions.publicSlashCommand
import dev.kordex.core.i18n.toKey
import live.einfachgustaf.mods.smp.utils.UUIDFetcher
import okhttp3.OkHttpClient

class WhitelistSetupCommand(override val name: String = "whitelistsetupcommand") : Extension() {

    override suspend fun setup() {

        publicSlashCommand {
            name = "whitelistsetup".toKey()
            description = "Richte die SMP Whitelist ein!".toKey()
            requirePermission(Permission.Administrator)

            action {
                respond {
                    components {
                        publicButton(::WhitelistModal) {
                            label = "🌏 Whitelist".toKey()
                            embed {
                                title = "🚀 Whitelist"
                                description = "Klicke auf den Button, um dich zu whitelisten!"
                                color = Color(84, 245, 66)
                                footer {
                                    text = "EinfachGustaf SMP | Whitelist"
                                }
                            }
                            action {
                                val playerName = it?.playerName?.value
                                val uuidFetcher = UUIDFetcher(OkHttpClient())
                                val key = it?.key?.value

                                if (playerName == null) {
                                    respond {
                                        embed {
                                            title = "Whitelist"
                                            description = "Failed to fetch player name."
                                            color = Color(245, 66, 66)
                                        }
                                    }
                                    return@action
                                }

                                if (key == null) {
                                    respond {
                                        embed {
                                            title = "Whitelist"
                                            description = "Failed to fetch key."
                                            color = Color(245, 66, 66)
                                        }
                                    }
                                    return@action
                                }

                                val uuid = uuidFetcher.fetchUUID(playerName)

                                if (uuid != null) {
                                    val betaKey = DiscordWhitelist.getKey(key)

                                    if (betaKey != null) {
                                        if (betaKey.isClaimed) {
                                            respond {
                                                embed {
                                                    title = "Whitelist"
                                                    description = "Key ||`$key`|| is already claimed."
                                                    color = Color(245, 66, 66)
                                                }
                                            }
                                            return@action
                                        }

                                        val betaKeyEdited = betaKey.copy(claimedBy = uuid)
                                        DiscordWhitelist.upsert(playerName, betaKeyEdited)

                                        respond {
                                            embed {
                                                title = "Whitelist"
                                                description = "Player $playerName has been whitelisted successfully!"
                                                color = Color(84, 245, 66)
                                            }
                                        }
                                    } else {
                                        respond {
                                            embed {
                                                title = "Whitelist"
                                                description = "Failed to find key ||`$betaKey`||."
                                                color = Color(245, 66, 66)
                                            }
                                        }
                                    }
                                } else {
                                    respond {
                                        embed {
                                            title = "Whitelist"
                                            description = "Failed to fetch UUID for player $playerName."
                                            color = Color(245, 66, 66)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
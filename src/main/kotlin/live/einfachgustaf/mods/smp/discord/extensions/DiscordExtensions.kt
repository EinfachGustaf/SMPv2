package live.einfachgustaf.mods.smp.discord.extensions

import dev.kord.rest.builder.message.EmbedBuilder

/**
 * Adds the EinfachGustaf icon to the footer of the embed.
 */
fun EmbedBuilder.Footer.einfachGustafIcon() {
    icon = "https://raw.githubusercontent.com/EinfachGustaf/SMPv2/refs/heads/main/.github/img/einfachgustaf-logo.png"
}
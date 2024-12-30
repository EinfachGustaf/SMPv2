package live.einfachgustaf.mods.smp.discord.whitelist

import dev.kordex.core.components.forms.ModalForm
import dev.kordex.core.i18n.toKey
import dev.kordex.core.i18n.types.Key

class WhitelistModal(override var title: Key = "Whitelist".toKey()): ModalForm() {

    val key = lineText {
        label = "Beta Key".toKey()
        placeholder = "Gib hier deinen Beta Key ein!".toKey()
    }

    val playerName = lineText {
        label = "Spielername".toKey()
        placeholder = "DerDino_Marlir".toKey()
    }

    val bedrock = paragraphText {
        label = "Bist du Bedrock Spieler?".toKey()
        placeholder = "Schreib hier etwas, wenn du Bedrock Spieler bist (Discord hat keine Option für an/aus Schalter. :/)!".toKey()
    }
}
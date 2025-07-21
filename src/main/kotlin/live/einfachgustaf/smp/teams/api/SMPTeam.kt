package live.einfachgustaf.smp.teams.api

import java.util.UUID

data class SMPTeam(
    val id: Int,
    val name: String,
    val shortName: String,
    val owner: UUID,
    var color: String,
    var maxPlayers: Int
) {

    fun update(): Boolean {
        TODO("not yet implemented")
    }
}
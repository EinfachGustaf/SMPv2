package live.einfachgustaf.smp.teams.api

import java.util.UUID

interface TeamManager {

    fun createTeam(
        name: String,
        shortName: String,
        owner: UUID,
        color: String = "#FFFFFF"
    ): Int?

    fun updateTeam(
        id: Int,
        team: SMPTeam
    ): Boolean
}
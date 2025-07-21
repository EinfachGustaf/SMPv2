package live.einfachgustaf.smp.teams.impl

import live.einfachgustaf.smp.teams.api.SMPTeam
import live.einfachgustaf.smp.teams.api.TeamManager
import java.util.UUID

class TeamManagerImpl: TeamManager {
    
    override fun createTeam(
        name: String,
        shortName: String,
        owner: UUID,
        color: String
    ): Int? {
        TODO("Not yet implemented")
    }

    override fun updateTeam(id: Int, team: SMPTeam) {
        TODO("Not yet implemented")
    }

}
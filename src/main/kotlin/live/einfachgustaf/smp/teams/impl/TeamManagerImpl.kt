package live.einfachgustaf.smp.teams.impl

import live.einfachgustaf.smp.teams.tables.TeamTable
import java.util.UUID
import live.einfachgustaf.smp.teams.api.SMPTeam
import live.einfachgustaf.smp.teams.api.TeamManager
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.neq
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.or
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update
import org.jetbrains.exposed.v1.jdbc.insert

class TeamManagerImpl: TeamManager {

    override fun createTeam(
        name: String,
        shortName: String,
        owner: UUID,
        color: String
    ): Int? = transaction {
        val exists = TeamTable.select(
            (TeamTable.name eq name) or (TeamTable.shortName eq shortName)
        ).count() > 0

        if (exists) return@transaction null

        val id = TeamTable.insert {
            it[TeamTable.name] = name
            it[TeamTable.shortName] = shortName
            it[TeamTable.owner] = owner
            it[TeamTable.color] = color
            it[TeamTable.maxPlayers] = 20 // TODO: config for default max players
        }[TeamTable.id]

        id
    }

    override fun updateTeam(id: Int, team: SMPTeam): Boolean = transaction {
        val exists = TeamTable.select(
            ((TeamTable.name eq team.name) or (TeamTable.shortName eq team.shortName)) and (TeamTable.id neq id)
        ).count() > 0

        if (exists) return@transaction false

        val updatedRows = TeamTable.update({ TeamTable.id eq id }) {
            it[TeamTable.name] = team.name
            it[TeamTable.shortName] = team.shortName
            it[TeamTable.owner] = team.owner
            it[TeamTable.color] = team.color
            it[TeamTable.maxPlayers] = team.maxPlayers
        }
        updatedRows > 0
    }
}
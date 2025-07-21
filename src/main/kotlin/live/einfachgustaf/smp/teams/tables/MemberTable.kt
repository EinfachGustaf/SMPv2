package live.einfachgustaf.smp.teams.tables

import org.jetbrains.exposed.v1.core.Table

object MemberTable : Table("smp_team_members") {
    val id = integer("id").autoIncrement() // primary key
    val uuid = uuid("uuid").uniqueIndex()
    val teamId = integer("team_id").references(TeamTable.id)
    override val primaryKey = PrimaryKey(id)
}
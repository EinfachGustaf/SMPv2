package live.einfachgustaf.smp.teams.tables

import org.jetbrains.exposed.v1.core.Table

object TeamTable : Table("smp_teams") {
    val id = integer("id").autoIncrement() // primary key
    val name = varchar("name", 32).uniqueIndex()
    val shortName = varchar("short_name", 4).uniqueIndex()
    val owner = uuid("owner")
    val color = varchar("color", 7) // as hex value
    val maxPlayers = integer("max_players")
}
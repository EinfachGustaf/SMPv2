package live.einfachgustaf.smp.teams

import live.einfachgustaf.smp.core.feature.Feature
import live.einfachgustaf.smp.core.feature.FeatureContext
import live.einfachgustaf.smp.teams.impl.TeamManagerImpl
import live.einfachgustaf.smp.teams.tables.MemberTable
import live.einfachgustaf.smp.teams.tables.TeamTable
import live.einfachgustaf.smp.utils.registerSimple
import live.einfachgustaf.smp.utils.servicesManager
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class TeamsFeature(override val name: String = "Teams") : Feature {

    override fun enable(context: FeatureContext) {
        transaction {
            SchemaUtils.create(TeamTable, MemberTable)
        }

        servicesManager.registerSimple(TeamManagerImpl())
    }

    override fun disable() = Unit
}
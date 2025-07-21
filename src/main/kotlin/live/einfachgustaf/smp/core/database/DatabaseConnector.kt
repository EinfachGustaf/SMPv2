package live.einfachgustaf.smp.core.database

import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.v1.jdbc.Database

class DatabaseConnector(plugin: JavaPlugin) {
    var database: Database
        private set

    init {
        plugin.logger.info("Connecting to database")
        database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver", user = "root", password = "") // TODO: currently only for testing
    }
}
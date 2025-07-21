package live.einfachgustaf.smp.core.database

import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.v1.jdbc.Database

/**
 * Handles the initialization and connection to a database instance for the plugin.
 * Currently connects to an in-memory H2 database for testing purposes.
 *
 * @constructor Initializes the database connection and logs the operation.
 * @param plugin The plugin instance used to access the logger and context.
 */
class DatabaseConnector(plugin: JavaPlugin) {

    /**
     * The connected database instance.
     * This is initialized during construction and cannot be modified externally.
     */
    var database: Database
        private set

    init {
        plugin.logger.info("Connecting to database")
        database = Database.connect(
            url = "jdbc:h2:mem:test",
            driver = "org.h2.Driver",
            user = "root",
            password = ""
        ) // TODO: currently only for testing
    }
}

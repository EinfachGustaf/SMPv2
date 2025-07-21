package live.einfachgustaf.smp.features.advancements.api

import org.bukkit.plugin.Plugin

/**
 * Provides contextual information and access to server components
 * specifically for advancements-related operations.
 *
 * @property plugin The plugin instance that created this context.
 *                  Used to retrieve references to the server and plugin manager.
 */
class AdvancementContext(
    val plugin: Plugin
) {
    /**
     * Reference to the Minecraft server instance.
     * Derived from the plugin and used for accessing server-wide functionality.
     */
    val server get() = plugin.server

    /**
     * Reference to the plugin manager responsible for handling plugin-related operations.
     * Retrieved via the server object.
     */
    val pluginManager get() = server.pluginManager
}
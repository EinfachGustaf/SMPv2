package live.einfachgustaf.smp.core.feature

import org.bukkit.Server
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager

/**
 * Provides contextual information and access to core server components
 * for enabling and managing features.
 *
 * @property plugin The plugin instance that created this context.
 *                  Used to retrieve references to the server and plugin manager.
 */
class FeatureContext(
    val plugin: Plugin
) {
    /**
     * Reference to the Minecraft server instance.
     * Derived from the plugin and used for accessing server-wide functionality.
     */
    val server: Server get() = plugin.server

    /**
     * Reference to the plugin manager responsible for handling plugin-related operations.
     * Retrieved via the server object.
     */
    val pluginManager: PluginManager get() = server.pluginManager
}

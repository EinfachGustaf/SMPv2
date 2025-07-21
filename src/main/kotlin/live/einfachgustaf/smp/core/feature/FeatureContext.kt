package live.einfachgustaf.smp.core.feature

import org.bukkit.Server
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager

class FeatureContext(
    val plugin: Plugin
) {
    val server: Server get() = plugin.server
    val pluginManager: PluginManager get() = server.pluginManager
}

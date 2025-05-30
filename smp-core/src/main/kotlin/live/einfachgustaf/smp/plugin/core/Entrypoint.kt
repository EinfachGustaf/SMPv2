package live.einfachgustaf.smp.plugin.core

import live.einfachgustaf.smp.mod.core.events.CorePluginLifecycleEvent
import live.einfachgustaf.smp.mod.core.types.CorePluginLifecycleType
import me.obsilabor.alert.EventManager
import org.bukkit.plugin.java.JavaPlugin

/**
 * Entrypoint for the SMP Core plugin.
 *
 * This class is used to handle the plugin lifecycle events such as loading, enabling, and disabling the plugin.
 */
@Suppress("unused") // This class is used as the main entry point for the plugin.
class Entrypoint: JavaPlugin() {

    companion object {
        /**
         * The instance of the Entrypoint class.
         *
         * This is used to access the plugin instance from anywhere in the code.
         */
        @get:JvmStatic
        lateinit var instance: Entrypoint
    }

    override fun onLoad() {
        EventManager.callEvent(CorePluginLifecycleEvent(this, CorePluginLifecycleType.LOAD))
        instance = this
    }

    override fun onEnable() {
        EventManager.callEvent(CorePluginLifecycleEvent(this, CorePluginLifecycleType.ENABLE))
    }

    override fun onDisable() {
        EventManager.callEvent(CorePluginLifecycleEvent(this, CorePluginLifecycleType.DISABLE))
    }
}

/**
 * The main entry point for the SMP Core plugin.
 *
 * This is used to access the plugin instance from anywhere in the code.
 */
val Plugin by lazy { Entrypoint.instance }
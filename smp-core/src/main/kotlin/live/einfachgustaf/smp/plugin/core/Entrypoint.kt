package live.einfachgustaf.smp.plugin.core

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import live.einfachgustaf.smp.mod.core.events.CorePluginLifecycleEvent
import live.einfachgustaf.smp.mod.core.types.CorePluginLifecycleType
import live.einfachgustaf.smp.plugin.core.commands.SpawnCommand
import me.obsilabor.alert.EventManager
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Location
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

    /**
     * The spawn location of the world named "world".
     *
     * This is used to get the spawn location of the world.
     */
    lateinit var spawnLocation: Location

    /**
     * The MiniMessage instance used for parsing and formatting messages.
     *
     * This is used to parse and format messages using the MiniMessage syntax.
     */
    val miniMessage = MiniMessage.miniMessage()

    override fun onLoad() {
        EventManager.callEvent(CorePluginLifecycleEvent(this, CorePluginLifecycleType.LOAD))
        instance = this
    }

    override fun onEnable() {
        EventManager.callEvent(CorePluginLifecycleEvent(this, CorePluginLifecycleType.ENABLE))

        spawnLocation = this.server.getWorld("world")?.spawnLocation ?: error("World 'world' not found. Please create a world named 'world'.")

        @Suppress("UnstableApiUsage")
        this.lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) {
            it.registrar().register("spawn", SpawnCommand())
        }
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
val SMPCore by lazy { Entrypoint.instance }
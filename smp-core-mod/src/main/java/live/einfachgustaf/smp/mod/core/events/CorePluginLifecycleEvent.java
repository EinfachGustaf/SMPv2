package live.einfachgustaf.smp.mod.core.events;

import live.einfachgustaf.smp.mod.core.types.CorePluginLifecycleType;
import me.obsilabor.alert.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * This event is fired when a core plugin's lifecycle changes.
 * It can be used to listen for when a core plugin is loaded, enabled, or disabled.
 */
public class CorePluginLifecycleEvent extends Event {

    private final Plugin plugin;
    private final CorePluginLifecycleType type;

    /**
     * Creates a new CorePluginLifecycleEvent.
     * @param plugin the plugin instance that is changing its lifecycle
     * @param type the type of lifecycle change
     */
    public CorePluginLifecycleEvent(@NotNull Plugin plugin, @NotNull CorePluginLifecycleType type) {
        this.plugin = plugin;
        this.type = type;
    }

    /**
     * Gets the plugin that is changing its lifecycle.
     * @return the plugin
     */
    @NotNull
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Gets the type of lifecycle change.
     * @return the lifecycle type
     */
    @NotNull
    public CorePluginLifecycleType getType() {
        return type;
    }
}

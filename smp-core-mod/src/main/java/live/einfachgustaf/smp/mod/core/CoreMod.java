package live.einfachgustaf.smp.mod.core;

import live.einfachgustaf.smp.mod.core.types.CorePluginLifecycleType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * CoreMod is a singleton class that represents the core mod of the SMP plugin.
 * It is responsible for managing the core plugin instance and its lifecycle.
 * This class should be initialized once during the plugin's startup.
 */
public class CoreMod {
    private static CoreMod instance;
    private Plugin corePlugin;
    private CorePluginLifecycleType lastLifecycleType = null;

    /**
     * Initializes the CoreMod singleton instance.
     * This method should be called once during the plugin's startup.
     * @param corePlugin the core plugin instance that this mod is associated with
     * @param lastLifecycleType the last lifecycle type of the core plugin can be null if not applicable
     */
    public static void initialize(@NotNull Plugin corePlugin, @Nullable CorePluginLifecycleType lastLifecycleType) {
        if (instance != null) {
            throw new IllegalStateException("CoreMod is already initialized!");
        }
        instance = new CoreMod(corePlugin, lastLifecycleType);
    }

    /**
     * Creates a new CoreMod instance.
     * @param corePlugin the core plugin instance that this mod is associated with
     */
    private CoreMod(@NotNull Plugin corePlugin, @Nullable CorePluginLifecycleType lastLifecycleType) {
        this.corePlugin = corePlugin;
        this.lastLifecycleType = lastLifecycleType;
    }

    /**
     * Gets the singleton instance of CoreMod.
     * @return the CoreMod instance
     */
    @Nullable
    public static CoreMod getInstance() {
        return instance;
    }

    /**
     * Gets the core plugin associated with this mod.
     * @return the core plugin
     */
    @NotNull
    public Plugin getCorePlugin() {
        return corePlugin;
    }

    /**
     * Returns the last lifecycle type of the core plugin.
     * @return the last lifecycle type
     */
    @Nullable
    public CorePluginLifecycleType getLastLifecycleType() {
        return lastLifecycleType;
    }

    /**
     * Sets the last lifecycle type of the core plugin.
     * @param lastLifecycleType the last lifecycle type to set
     */
    public void setLastLifecycleType(CorePluginLifecycleType lastLifecycleType) {
        this.lastLifecycleType = lastLifecycleType;
    }
}

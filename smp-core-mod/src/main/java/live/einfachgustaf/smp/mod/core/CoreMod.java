package live.einfachgustaf.smp.mod.core;

import live.einfachgustaf.smp.mod.core.types.CorePluginLifecycleType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoreMod {
    private static CoreMod instance;

    private Plugin corePlugin;
    private CorePluginLifecycleType lastLifecycleType = null;

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

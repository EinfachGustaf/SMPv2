package live.einfachgustaf.smp.plugin.discord;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entrypoint for the Discord plugin.
 * This class extends JavaPlugin and serves as the main entry point for the Discord plugin.
 * It provides access to the core plugin instance.
 */
public class Entrypoint extends JavaPlugin {
    private static Entrypoint instance;
    private live.einfachgustaf.smp.plugin.core.Entrypoint corePluginInstance;

    @Override
    public void onLoad() {
        instance = this;
        corePluginInstance = live.einfachgustaf.smp.plugin.core.Entrypoint.getInstance();
    }

    /**
     * Returns the singleton instance of the Entrypoint class.
     * @return the Entrypoint instance
     */
    public static Entrypoint getInstance() {
        return instance;
    }

    /**
     * Returns the core plugin instance.
     * @return the core plugin instance
     */
    public live.einfachgustaf.smp.plugin.core.Entrypoint getCorePluginInstance() {
        return corePluginInstance;
    }
}

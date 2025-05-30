package live.einfachgustaf.smp.plugin.discord;

import live.einfachgustaf.smp.plugin.discord.bot.DiscordBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entrypoint for the Discord plugin.
 * This class extends JavaPlugin and serves as the main entry point for the Discord plugin.
 * It provides access to the core plugin instance.
 */
public class Entrypoint extends JavaPlugin {
    private static Entrypoint instance;
    private live.einfachgustaf.smp.plugin.core.Entrypoint corePluginInstance;
    private JDA jda;

    @Override
    public void onLoad() {
        instance = this;
        corePluginInstance = live.einfachgustaf.smp.plugin.core.Entrypoint.getInstance();
    }

    @Override
    public void onEnable() {
        provideJda();
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

    /**
     * Returns the JDA instance used by the Discord plugin.
     * @return the JDA instance
     */
    public JDA getJda() {
        return jda;
    }

    private void provideJda() {
        if (jda != null) {
            getLogger().warning("JDA instance is already provided.");
            return;
        }

        try {
            jda = JDABuilder.createDefault(System.getenv("DISCORD_BOT_TOKEN")) // TODO: Create a config file for the token
                    .addEventListeners(new DiscordBot(jda)) // Register the DiscordBot as an event listener
                    .build();
            jda.awaitReady(); // Wait for JDA to be ready
            getLogger().info("Discord plugin enabled successfully.");
        } catch (IllegalArgumentException e) {
            getLogger().severe("Failed to enable Discord plugin: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            getLogger().severe("Discord plugin initialization was interrupted: " + e.getMessage());
        } catch (Exception e) {
            getLogger().severe("An unexpected error occurred while enabling the Discord plugin: " + e.getMessage());
        }
    }
}

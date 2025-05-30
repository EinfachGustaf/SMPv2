package live.einfachgustaf.smp.plugin.discord.bot;

import live.einfachgustaf.smp.plugin.discord.Entrypoint;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

/**
 * DiscordBot class implements EventListener to handle Discord events.
 * It listens for the ReadyEvent to log when the bot is ready.
 */
public class DiscordBot implements EventListener {
    private final JDA jda;

    public DiscordBot(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof ReadyEvent) {
            Entrypoint.getInstance().getLogger().info("DiscordBot is ready");
        }
    }

    /**
     * Returns the JDA instance used by the DiscordBot.
     * @return the JDA instance
     */
    public JDA getJda() {
        return jda;
    }
}

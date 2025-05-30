package live.einfachgustaf.smp.mod.core.listener;

import live.einfachgustaf.smp.mod.core.CoreMod;
import live.einfachgustaf.smp.mod.core.events.CorePluginLifecycleEvent;
import live.einfachgustaf.smp.mod.core.types.CorePluginLifecycleType;
import me.obsilabor.alert.Subscribe;

public class CorePluginLifecycleListener {

    @Subscribe
    public void handle(CorePluginLifecycleEvent event) {
        if (event.getType() == CorePluginLifecycleType.LOAD) {
            CoreMod.initialize(event.getPlugin(), event.getType());
            return;
        }

        assert CoreMod.getInstance() != null;
        CoreMod.getInstance().getLogger().info("Received CorePluginLifecycleEvent: " + event.getType() + " for plugin: " + event.getPlugin().getName());
        CoreMod.getInstance().setLastLifecycleType(event.getType());
    }
}
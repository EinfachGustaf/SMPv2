package live.einfachgustaf.smp.mod.core.mixins;

import live.einfachgustaf.smp.mod.core.listener.CorePluginLifecycleListener;
import me.obsilabor.alert.EventManager;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    @Inject(at = @At("HEAD"), method = "runServer")
    public void injectRunServer(CallbackInfo ci) {
        EventManager.registerListener(new CorePluginLifecycleListener());
    }
}

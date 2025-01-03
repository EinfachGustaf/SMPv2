package live.einfachgustaf.mods.smp.mixinkt

import live.einfachgustaf.mods.smp.utils.miniMessage
import live.einfachgustaf.mods.smp.extensions.nativeComponent
import net.silkmc.silk.core.Silk
import net.minecraft.network.chat.Component as MinecraftComponent
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

object PlayerChatMessageHook {

    fun injectDecoratedContent(cir: CallbackInfoReturnable<MinecraftComponent>) {
        if (Silk.server == null) return
        val originalComponent = cir.returnValue
        if (originalComponent.toString().contains("style")) return // Message already converted, prevent recursion
        val originalText = originalComponent.string
        val translatedComponent = miniMessage.deserialize(originalText)
        cir.returnValue = translatedComponent.nativeComponent(Silk.server!!.registryAccess())
    }
}
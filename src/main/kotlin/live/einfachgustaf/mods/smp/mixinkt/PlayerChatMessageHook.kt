package live.einfachgustaf.mods.smp.mixinkt

import live.einfachgustaf.mods.smp.utils.miniMessage
import net.kyori.adventure.text.Component as AdventureComponent
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component.Serializer
import net.minecraft.network.chat.MutableComponent
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
        cir.returnValue = convertAdventureToMinecraft(translatedComponent, Silk.server!!.registryAccess())
    }

    fun convertAdventureToMinecraft(adventureComponent: AdventureComponent, provider: HolderLookup.Provider): MutableComponent? {
        val json = GsonComponentSerializer.gson().serialize(adventureComponent)
        return Serializer.fromJson(json, provider)
    }
}
package live.einfachgustaf.mods.smp.extensions

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component.Serializer
import net.minecraft.network.chat.MutableComponent

/**
 * Converts a Adventure Component to a native Minecraft Component.
 *
 * @param provider The HolderLookup.Provider to use for the conversion. (e.g. Silk.server!!.registryAccess())
 * @return The converted MutableComponent.
 */
fun Component.nativeComponent(provider: HolderLookup.Provider): MutableComponent? {
    val json = GsonComponentSerializer.gson().serialize(this)
    return Serializer.fromJson(json, provider)
}
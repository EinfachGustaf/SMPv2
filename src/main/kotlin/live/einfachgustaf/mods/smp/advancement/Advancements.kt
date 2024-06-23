package live.einfachgustaf.mods.smp.advancement

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import live.einfachgustaf.mods.smp.LOGGER
import net.minecraft.advancements.*
import net.minecraft.advancements.critereon.ImpossibleTrigger
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.silkmc.silk.core.task.mcCoroutineScope
import java.util.*

object Advancements {
    val DEFAULT_RESOURCE = ResourceLocation("textures/gui/advancements/backgrounds/adventure.png")

    private lateinit var root: AdvancementHolder
    private val advancements = mutableListOf<AdvancementHolder>()

    fun advancements(): List<AdvancementHolder> {
        return advancements
    }

    fun advancement(resourceLocation: ResourceLocation): AdvancementHolder? {
        return advancements.firstOrNull { it.id == resourceLocation }
    }

    fun advancement(path: String): AdvancementHolder? {
        return advancements.firstOrNull { it.id == res(path) }
    }

    private fun res(path: String): ResourceLocation {
        return ResourceLocation("einfachgustaf:/root/$path")
    }

    fun createTab(forAdvancement: GustafAdvancement): AdvancementHolder {
        val entry = Advancement.Builder.advancement()
            .display(DisplayInfo(
                forAdvancement.displayIcon,
                forAdvancement.title,
                forAdvancement.description,
                Optional.of(forAdvancement.backgroundResource),
                forAdvancement.type,
                false,
                true,
                true
            ))
            .addCriterion("dummy", CriteriaTriggers.IMPOSSIBLE.createCriterion(ImpossibleTrigger.TriggerInstance()))
        val advancementHolder = entry.build(res(""))
        advancements += advancementHolder
        root = advancementHolder
        return advancementHolder
    }

    fun register(forAdvancement: GustafAdvancement, path: String, parent: AdvancementHolder? = null, x: Float = 0f, y: Float = 0f): AdvancementHolder {
        val entry = Advancement.Builder.advancement()
            .display(DisplayInfo(
                forAdvancement.displayIcon,
                forAdvancement.title,
                forAdvancement.description,
                Optional.of(forAdvancement.backgroundResource),
                forAdvancement.type,
                true,
                true,
                false
            ).location(x, y))
            .addCriterion("dummy", CriteriaTriggers.IMPOSSIBLE.createCriterion(ImpossibleTrigger.TriggerInstance()))
        if (parent != null) {
            entry.parent(parent)
        }
        val advancementHolder = entry.build(res(path))
        advancements += advancementHolder
        return advancementHolder
    }

    fun createAdvancements(serverPlayer: ServerPlayer) {
        LOGGER.info("create")
        serverPlayer.connection.send(
            ClientboundUpdateAdvancementsPacket(
                false,
                advancements,
                setOf(),
                //advancements.associate { it.id to AdvancementProgress() }
                mapOf()
            )
        )
        mcCoroutineScope.launch {
            delay(1000)
            awardAdvancement(serverPlayer, root)
        }
    }

    fun awardAdvancement(serverPlayer: ServerPlayer, advancement: AdvancementHolder) {
        val progress = AdvancementProgress()
        val requirements = AdvancementRequirements.allOf(listOf("dummy"))
        progress.update(requirements)
        progress.grantProgress("dummy")
        serverPlayer.connection.send(
            ClientboundUpdateAdvancementsPacket(
                false,
                listOf(advancement),
                setOf(),
                mapOf(advancement.id to progress)
            )
        )
    }

    fun DisplayInfo.location(x: Float, y: Float): DisplayInfo {
        setLocation(x, y)
        return this
    }
}
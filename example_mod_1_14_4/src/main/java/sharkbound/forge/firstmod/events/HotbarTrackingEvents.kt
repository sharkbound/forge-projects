package sharkbound.forge.firstmod.events

import net.minecraft.util.Hand
import net.minecraft.util.math.RayTraceContext
import net.minecraft.util.math.RayTraceResult
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.InputEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.data.*
import sharkbound.forge.firstmod.items.MehWand
import sharkbound.forge.shared.extensions.*
import kotlin.contracts.ExperimentalContracts


@Mod.EventBusSubscriber
object HotbarTrackingEvents {
    init {
        forgeEventBus.register(this)
    }

    @OnlyIn(Dist.CLIENT)
    @ExperimentalContracts
    @SubscribeEvent(priority = EventPriority.LOWEST)
    // fixme send packet to server
    fun mouseInput(e: InputEvent.MouseScrollEvent) {
        val player = mcPlayer
        if (!e.anyMouseKeyDown && player.isSneaking && minecraft.objectMouseOver.type == RayTraceResult.Type.MISS) {
            val item = player.mainHand
            if (item isNotItem ModItems.MEH_WAND) return
            MehWand.modeOf(item).also {
                MehWand.setMode(item, when (e.scrollDirection) {
                    ScrollDirection.UP -> it.prev()
                    ScrollDirection.DOWN -> it.next()
                })
            }
            minecraft.gameRenderer.itemRenderer.resetEquippedProgress(Hand.MAIN_HAND)
            e.cancel()
        }
    }
}

package sharkbound.forge.firstmod.events

import net.minecraft.util.Hand
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.InputEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.objects.ModItems
import sharkbound.forge.firstmod.objects.forgeEventBus
import sharkbound.forge.firstmod.objects.mcPlayer
import sharkbound.forge.firstmod.objects.minecraft
import sharkbound.forge.firstmod.items.MehWand
import sharkbound.forge.shared.extensions.ScrollDirection
import sharkbound.forge.shared.extensions.anyMouseKeyDown
import sharkbound.forge.shared.extensions.cancel
import sharkbound.forge.shared.extensions.isNotItem
import sharkbound.forge.shared.extensions.mainHand
import sharkbound.forge.shared.extensions.scrollDirection
import kotlin.contracts.ExperimentalContracts

@Mod.EventBusSubscriber
object HotbarTrackingEvents {
    init {
        forgeEventBus.register(this)
    }

    @OnlyIn(Dist.CLIENT)
    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun mouseInput(e: InputEvent.MouseScrollEvent) {
        val player = mcPlayer
        if (!e.anyMouseKeyDown && player.isSneaking) {
            val item = player.mainHand
            if (item isNotItem ModItems.MEH_WAND) return
            MehWand.modeOf(item).also {
                MehWand.setMode(item, when (e.scrollDirection) {
                    ScrollDirection.UP -> it.prev()
                    ScrollDirection.DOWN -> it.next()
                })
            }
            minecraft.gameRenderer.itemRenderer.resetEquippedProgress(Hand.MAIN_HAND)
            MehWand.sendModeUpdateMessage(player, item)
            MehWand.sendModeUpdatePacket(player.uniqueID, MehWand.modeOf(item))
            e.cancel()
        }
    }
}

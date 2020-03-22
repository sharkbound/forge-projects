package sharkbound.forge.firstmod.events

import net.minecraft.util.Hand
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

    // fixme
//    @OnlyIn(Dist.CLIENT)
//    @ExperimentalContracts
//    @SubscribeEvent(priority = EventPriority.LOWEST)
//    fun mouseInput(e: InputEvent.MouseScrollEvent) {
//        val player = mcPlayer
//        val item = player.mainHand
//        if (!e.isLeftDown && !e.isMiddleDown && !e.isRightDown && player.isSneaking && item itemIs ModItems.MEH_WAND) {
//            MehWand.modeOf(item).also { MehWand.setMode(item, if (e.scrollDelta < 0) it.prev() else it.next()) }
//            ModItems.MEH_WAND.sendModeUpdateMessage(player, item)
//            e.cancel()
//        }
//    }
}
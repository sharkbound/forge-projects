package sharkbound.forge.firstmod.events

import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.data.forgeEventBus
import sharkbound.forge.firstmod.data.minecraft
import sharkbound.forge.shared.extensions.*
import kotlin.contracts.ExperimentalContracts

@Mod.EventBusSubscriber
object HotbarTrackingEvents {
    init {
        forgeEventBus.register(this)
    }

    @SubscribeEvent
    fun onLogin(e: PlayerEvent.PlayerLoggedInEvent) {
        e.player.send("hello ${e.player.name.formattedText}!")
    }


//    var last = -1

    @ExperimentalContracts
    @SubscribeEvent
    fun onLivingEntityUpdate(e: LivingEvent.LivingUpdateEvent) {
//        e.entity.ifServerPlayer {
//            if (last == -1) {
//                last = inventory.currentItem
//            }
//            if (last != inventory.currentItem) {
////                send("canceling")
////                e.isCanceled = true // no work
//            }
//        }
    }
}
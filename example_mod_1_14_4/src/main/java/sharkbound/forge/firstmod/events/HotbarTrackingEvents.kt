package sharkbound.forge.firstmod.events

import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.data.forgeEventBus
import sharkbound.forge.shared.extensions.send
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
    fun onLivingEntityUpdate(e: PlayerInteractEvent) {
//        e ifNotCanceled {
//            player.send("${entity.name.formattedText} $itemStack ${e.javaClass.simpleName}")
//        }
    }
}
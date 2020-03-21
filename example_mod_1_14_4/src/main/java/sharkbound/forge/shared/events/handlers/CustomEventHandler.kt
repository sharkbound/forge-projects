package sharkbound.forge.shared.events.handlers

import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
object CustomEventHandler {
    @SubscribeEvent
    fun onLogin(e: PlayerEvent.PlayerLoggedInEvent) {

    }

    @SubscribeEvent
    fun onLivingEntityUpdate(e: LivingEvent.LivingUpdateEvent) {
        // todo
    }
}
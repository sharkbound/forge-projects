package sharkbound.forge.firstmod.events

import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.objects.forgeEventBus

@Mod.EventBusSubscriber
object PlayerEvents {
    init {
        forgeEventBus.register(this)
    }

    @SubscribeEvent
    @JvmStatic
    fun onPlayerTick(e: TickEvent.PlayerTickEvent) {
    }
}
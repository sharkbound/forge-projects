package sharkbound.forge.firstmod.events

import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.data.forgeEventBus

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
object PlayerEvents {
    init {
        forgeEventBus.register(this)
    }

    @SubscribeEvent
    fun tick(e: TickEvent.PlayerTickEvent) {
//        e.player.send(e.player.heldItemInfo itemIs ModItems.FIRST_ITEM)
    }
}
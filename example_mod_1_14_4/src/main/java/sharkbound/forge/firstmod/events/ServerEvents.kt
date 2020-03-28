package sharkbound.forge.firstmod.events

import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.data.forgeEventBus

@Mod.EventBusSubscriber
object ServerEvents {
    init {
        forgeEventBus.register(this)
    }

    val tickHandlers = mutableListOf<TickHandler>()
    val completedHandlers = mutableListOf<TickHandler>()

    var ticks = 0

    @SubscribeEvent
    @JvmStatic
    fun onServerTick(e: TickEvent.ServerTickEvent) {
        for (tickHandler in tickHandlers) {
            if (tickHandler.state == TickHandler.State.COMPLETED) {
                completedHandlers.add(tickHandler)
                continue
            }
            tickHandler.tick()
        }

        completedHandlers.let {
            if (it.isNotEmpty()) {
                tickHandlers.removeAll(it)
                it.clear()
            }
        }
    }
}


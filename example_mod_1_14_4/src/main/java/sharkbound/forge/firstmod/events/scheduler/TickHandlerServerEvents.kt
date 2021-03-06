package sharkbound.forge.firstmod.events.scheduler

import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.server.FMLServerStartedEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent
import sharkbound.forge.firstmod.commands.ModCommands
import sharkbound.forge.firstmod.objects.forgeEventBus
import sharkbound.forge.shared.util.events.Server
import sharkbound.forge.shared.util.events.ServerState
import kotlin.contracts.ExperimentalContracts

@Mod.EventBusSubscriber
object TickHandlerServerEvents {

    init {
        forgeEventBus.register(this)
    }

    val tickHandlerAddQueue = mutableListOf<TickHandler>()
    val tickHandlers = mutableListOf<TickHandler>()
    private val completedHandlers = mutableListOf<TickHandler>()

    @SubscribeEvent
    @JvmStatic
    fun onServerShutdown(e: FMLServerStoppingEvent) {
        tickHandlerAddQueue.clear()
        tickHandlers.clear()
        completedHandlers.clear()
    }

    @SubscribeEvent
    @JvmStatic
    fun onServerStarted(e: FMLServerStartedEvent) {
        tickHandlerAddQueue.clear()
        tickHandlers.clear()
        completedHandlers.clear()
    }

    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun onServerStarting(e: FMLServerStartingEvent) {
        ModCommands.register(e.commandDispatcher)
    }

    @SubscribeEvent
    @JvmStatic
    fun taskServerTickProcessor(e: TickEvent.ServerTickEvent) {
        if (e.side == LogicalSide.CLIENT || Server.state != ServerState.STARTED) return

        for (handler in tickHandlers) {
            if (handler.state == TickHandler.State.COMPLETED) {
                completedHandlers.add(handler)
                continue
            }
            handler.tick()
        }

        completedHandlers.let {
            if (it.isNotEmpty()) {
                tickHandlers.removeAll(it)
                it.clear()
            }
        }

        tickHandlerAddQueue.let {
            tickHandlers.addAll(it)
            it.clear()
        }
    }
}


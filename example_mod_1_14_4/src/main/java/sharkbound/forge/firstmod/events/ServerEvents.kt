package sharkbound.forge.firstmod.events

import net.minecraft.entity.EntityType
import net.minecraft.entity.projectile.PotionEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.potion.PotionUtils
import net.minecraft.potion.Potions
import net.minecraft.world.dimension.DimensionType
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.server.FMLServerStartedEvent
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent
import sharkbound.commonutils.util.randDouble
import sharkbound.commonutils.util.randInt
import sharkbound.forge.firstmod.data.forgeEventBus
import sharkbound.forge.firstmod.util.delayRepeatingTask
import sharkbound.forge.firstmod.util.delayTask
import sharkbound.forge.shared.extensions.instance
import sharkbound.forge.shared.extensions.send
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.playerList
import sharkbound.forge.shared.util.server

@Mod.EventBusSubscriber
object ServerEvents {
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

    @SubscribeEvent
    @JvmStatic
    fun taskServerTickProcessor(e: TickEvent.ServerTickEvent) {
        if (e.side == LogicalSide.CLIENT) return
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

        tickHandlerAddQueue.let {
            tickHandlers.addAll(it)
            it.clear()
        }
    }
}


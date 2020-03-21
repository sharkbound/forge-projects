package sharkbound.forge.firstmod.events

import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.data.ModItems
import sharkbound.forge.firstmod.data.forgeEventBus
import sharkbound.forge.shared.extensions.*
import kotlin.contracts.ExperimentalContracts

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
object PlayerEvents {
    init {
        forgeEventBus.register(this)
    }
}
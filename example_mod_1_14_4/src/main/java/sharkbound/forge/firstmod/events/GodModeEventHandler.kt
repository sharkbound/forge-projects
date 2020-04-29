package sharkbound.forge.firstmod.events

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.commands.GodCommand
import sharkbound.forge.shared.extensions.cancel
import sharkbound.forge.shared.extensions.isServerWorld
import kotlin.contracts.ExperimentalContracts

@Mod.EventBusSubscriber
object GodModeEventHandler {
    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun onPlayerDamage(e: LivingDamageEvent) {
        val entity = e.entity
        if (entity is ServerPlayerEntity && entity.world.isServerWorld()) {
            if (GodCommand.isInGod(entity)) e.cancel()
        }
    }
}
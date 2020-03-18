package sharkbound.forge.firstmod.events

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.potion.*
import net.minecraft.util.DamageSource
import net.minecraft.util.EntityDamageSource
import net.minecraft.util.math.Vec3d
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.DeferredWorkQueue
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.data.forgeEventBus
import sharkbound.forge.firstmod.data.logger
import sharkbound.forge.firstmod.items.FirstItem
import sharkbound.forge.shared.extensions.ifServerPlayer
import sharkbound.forge.shared.extensions.isServerPlayer
import kotlin.contracts.ExperimentalContracts

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
object PlayerEvents {
    init {
        forgeEventBus.register(this)
    }


/*
    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun onPlayerTick(e: TickEvent.PlayerTickEvent) {
        e.player ifServerPlayer {
            if (!isPotionActive(Effects.STRENGTH)) {
                addPotionEffect(EffectInstance(Effects.STRENGTH, 100, 100, true, false))
            }
        }
    }
*/

    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun onEntityDamaged(e: LivingDamageEvent) {
        if (!e.entity.isServerPlayer() && e.source is EntityDamageSource) {
            e.source.trueSource.let {
                if (it.isServerPlayer() && it.inventory.getCurrentItem().item is FirstItem) {
                    e.amount = 0f
//                   fixme
                    e.entityLiving.run {
                        if (!world.isRemote) {
                            fall(1f, 0f)
                            addVelocity(0.0, 3.0, 0.0)
                            velocityChanged = true
                        }
                    }
                }
            }
        }
    }
}
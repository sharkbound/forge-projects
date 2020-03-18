package sharkbound.forge.firstmod.potions

import net.minecraft.client.particle.Particle
import net.minecraft.entity.LivingEntity
import net.minecraft.particles.ParticleTypes
import net.minecraft.potion.Effect
import net.minecraft.potion.EffectType
import net.minecraft.world.server.ServerWorld
import sharkbound.commonutils.util.randInt
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.teleportRelative
import kotlin.contracts.ExperimentalContracts

class ChaosEffect : Effect(EffectType.NEUTRAL, 0xff0000) {

    override fun isReady(duration: Int, amplifier: Int): Boolean {
        return true
    }

    @ExperimentalContracts
    override fun performEffect(e: LivingEntity, amplifier: Int) {
        e.world.let {
            if (it.isServerWorld()) {
                it.spawnParticle(
                        ParticleTypes.FLAME,
                        e.posX + randInt(-2, 2),
                        e.posY + randInt(0, 2),
                        e.posZ + randInt(-2, 2),
                        1,
                        0.0,
                        0.0,
                        0.0,
                        0.0
                )
            }
        }
    }
}
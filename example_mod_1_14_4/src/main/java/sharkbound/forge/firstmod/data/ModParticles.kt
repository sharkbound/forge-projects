package sharkbound.forge.firstmod.data

import net.minecraft.particles.ParticleType
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.particles.MehParticle

@Mod.EventBusSubscriber
object ModParticles {
    @ObjectHolder("$MOD_ID:meh")
    @JvmStatic
    lateinit var MEH: ParticleType<MehParticle>
}
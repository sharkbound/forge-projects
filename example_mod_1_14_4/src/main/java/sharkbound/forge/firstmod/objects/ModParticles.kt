package sharkbound.forge.firstmod.objects

import net.minecraft.particles.BasicParticleType
import net.minecraft.particles.ParticleType
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.particles.data.MehBoltParticleData

@OnlyIn(Dist.CLIENT)
object ModParticles {
    private val PARTICLE_TYPES = DeferredRegister(ForgeRegistries.PARTICLE_TYPES, MOD_ID)
    
    fun init() {
        PARTICLE_TYPES.register(modEventBus)
    }

    val MEH = PARTICLE_TYPES.register("meh") { BasicParticleType(true) }
    val MEH_BOLT = PARTICLE_TYPES.register("meh_bolt") { ParticleType(false, MehBoltParticleData.DESERIALIZER()) }
}
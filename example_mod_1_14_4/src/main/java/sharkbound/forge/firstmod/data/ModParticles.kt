package sharkbound.forge.firstmod.data

import net.minecraft.particles.BasicParticleType
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.registries.*
import sharkbound.forge.firstmod.MOD_ID

@OnlyIn(Dist.CLIENT)
object ModParticles {
    fun init() {
        registry.register(modEventBus)
    }

    private val registry = DeferredRegister(ForgeRegistries.PARTICLE_TYPES, MOD_ID)

    val MEH = registry.register("meh") { BasicParticleType(true) }
}
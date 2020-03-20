package sharkbound.forge.firstmod.data

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import sharkbound.forge.firstmod.MOD_ID

@Mod.EventBusSubscriber
object ModParticles {
    private val registry = DeferredRegister(ForgeRegistries.PARTICLE_TYPES, MOD_ID)

}
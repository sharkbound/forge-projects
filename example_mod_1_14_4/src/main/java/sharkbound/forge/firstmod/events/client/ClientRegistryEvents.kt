package sharkbound.forge.firstmod.events.client

import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.objects.ModParticles
import sharkbound.forge.firstmod.objects.minecraft
import sharkbound.forge.firstmod.particles.MehBoltParticle
import sharkbound.forge.firstmod.particles.MehParticle

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = MOD_ID)
object ClientRegistryEvents {
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SubscribeEvent
    @JvmStatic
    fun registerClientParticles(e: ParticleFactoryRegisterEvent) {
        minecraft.particles.registerFactory(ModParticles.MEH.get()) { it -> MehParticle.Factory(it) }
        minecraft.particles.registerFactory(ModParticles.MEH_BOLT.get()) { it -> MehBoltParticle.Factory(it) }
    }
}
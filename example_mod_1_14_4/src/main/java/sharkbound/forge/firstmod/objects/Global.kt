package sharkbound.forge.firstmod.objects

import net.minecraft.client.Minecraft
import net.minecraft.particles.ParticleType
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.IForgeRegistry
import org.apache.logging.log4j.LogManager
import sharkbound.forge.firstmod.proxy.ClientProxy
import sharkbound.forge.firstmod.proxy.CommonProxy
import sharkbound.forge.firstmod.proxy.Proxy
import java.util.function.Supplier

val logger get() = LogManager.getLogger()!!

val modLoadingContext get() = FMLJavaModLoadingContext.get()

val modEventBus get() = modLoadingContext.modEventBus

val forgeEventBus get() = MinecraftForge.EVENT_BUS

val minecraft get() = Minecraft.getInstance()

val mcPlayer get() = minecraft.player

val proxy: Proxy by lazy {
    DistExecutor.runForDist<Proxy>({ Supplier { ClientProxy() } }, { Supplier { CommonProxy() } })
}

val forgeParticleRegistry: IForgeRegistry<ParticleType<*>>
    get() = ForgeRegistries.PARTICLE_TYPES
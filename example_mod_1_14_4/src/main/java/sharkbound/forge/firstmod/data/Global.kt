package sharkbound.forge.firstmod.data

import net.minecraft.client.Minecraft
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager
import sharkbound.forge.firstmod.proxy.*
import java.util.function.Supplier

val logger get() = LogManager.getLogger()!!

val modLoadingContext get() = FMLJavaModLoadingContext.get()

val modEventBus get() = modLoadingContext.modEventBus

val forgeEventBus get() = MinecraftForge.EVENT_BUS

val minecraft get() = Minecraft.getInstance()


val proxy: Proxy by lazy {
    DistExecutor.runForDist<Proxy>({ Supplier { ClientProxy() } }, { Supplier { ServerProxy() } })
}
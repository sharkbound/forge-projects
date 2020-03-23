package sharkbound.forge.firstmod.proxy

import net.minecraft.world.World
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

interface Proxy {
    val world: World
    fun init()
    fun commonSetup(e: FMLCommonSetupEvent) {}
}
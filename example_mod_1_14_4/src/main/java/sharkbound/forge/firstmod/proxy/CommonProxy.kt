package sharkbound.forge.firstmod.proxy

import net.minecraft.world.World
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.networking.Network

open class CommonProxy : Proxy {
    override val world: World
        get() = error("PROXY ERROR: trying to access client world from server")

    override fun init() {
    }

    override fun commonSetup(e: FMLCommonSetupEvent) {
        FirstModItemGroup.init()
        Network.init()
    }
}
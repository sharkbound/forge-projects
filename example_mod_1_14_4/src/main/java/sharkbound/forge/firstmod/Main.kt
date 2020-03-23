package sharkbound.forge.firstmod

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.data.*
import sharkbound.forge.firstmod.networking.Network

const val MOD_ID = "firstmod"
const val MOD_NAME = "First Mod"

@Mod(MOD_ID)
class FirstMod {
    init {
        modEventBus.addListener<FMLCommonSetupEvent> { setup(it) }
    }

    private fun setup(e: FMLCommonSetupEvent) {
        FirstModItemGroup.init()
        Network.init()
        proxy.init()
    }
}


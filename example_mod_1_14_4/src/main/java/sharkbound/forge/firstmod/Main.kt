package sharkbound.forge.firstmod

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import sharkbound.forge.firstmod.objects.modEventBus
import sharkbound.forge.firstmod.objects.proxy

const val MOD_ID = "firstmod"
const val MOD_NAME = "First Mod"

@Mod(MOD_ID)
class FirstMod {
    init {
        modEventBus.addListener<FMLCommonSetupEvent> { proxy.commonSetup(it) }
        proxy.init()
    }
}


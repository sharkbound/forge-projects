package sharkbound.forge.firstmod

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.ForgeRegistry
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.data.*
import sharkbound.forge.firstmod.potions.ChaosEffect

const val MOD_ID = "firstmod"
const val MOD_NAME = "First Mod"

@Mod(MOD_ID)
class FirstMod {
    init {
        modEventBus.addListener<FMLCommonSetupEvent> { setup(it) }
    }

    private fun setup(e: FMLCommonSetupEvent) {
//        ForgeRegistries.POTIONS.register(ChaosEffect())
        FirstModItemGroup.init()
        proxy.init()
    }
}


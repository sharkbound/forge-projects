package sharkbound.forge.firstmod.proxy

import net.minecraft.world.World
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import sharkbound.forge.firstmod.data.ModParticles
import sharkbound.forge.firstmod.data.minecraft

class ClientProxy : CommonProxy() {
    override fun init() {
        super.init()
        ModParticles.init()
    }

    override fun commonSetup(e: FMLCommonSetupEvent) {
        super.commonSetup(e)
    }

    override val world: World
        get() = minecraft.world
}
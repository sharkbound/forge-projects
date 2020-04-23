package sharkbound.forge.firstmod.proxy

import net.minecraft.world.World
import net.minecraftforge.fml.LogicalSide
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import sharkbound.forge.firstmod.objects.ModParticles
import sharkbound.forge.firstmod.objects.minecraft
import sharkbound.forge.firstmod.gui.ModContainers

class ClientProxy : CommonProxy() {
    override val side: LogicalSide = LogicalSide.CLIENT
    
    override fun init() {
        super.init()
        ModParticles.init()
    }

    override fun commonSetup(e: FMLCommonSetupEvent) {
        ModContainers.init()
        super.commonSetup(e)
    }

    override val world: World
        get() = minecraft.world
}
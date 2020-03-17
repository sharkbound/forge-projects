package sharkbound.forge.firstmod.blocks

import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import net.minecraftforge.registries.ObjectHolder

@Mod.EventBusSubscriber
object ModBlocks {
    @ObjectHolder("$MOD_ID:firstblock")
    @JvmStatic
    lateinit var FIRST_BLOCK: FirstBlock
}
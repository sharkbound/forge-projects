package sharkbound.forge.firstmod.blocks

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID

@Mod.EventBusSubscriber
object ModBlocks {
    @ObjectHolder("$MOD_ID:firstblock")
    @JvmStatic
    lateinit var FIRST_BLOCK: FirstBlock
}
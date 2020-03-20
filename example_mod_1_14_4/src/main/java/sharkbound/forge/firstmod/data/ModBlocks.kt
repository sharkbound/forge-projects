package sharkbound.forge.firstmod.data

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.blocks.MehBlock
import sharkbound.forge.firstmod.entities.FirstBlockTileEntity

@Mod.EventBusSubscriber
object ModBlocks {
    @ObjectHolder("$MOD_ID:mehblock")
    @JvmStatic
    lateinit var MEH_BLOCK: MehBlock


    @ObjectHolder("$MOD_ID:mehblock")
    @JvmStatic
    lateinit var FIRST_BLOCK_TILE_ENTITY: TileEntityType<FirstBlockTileEntity>
}

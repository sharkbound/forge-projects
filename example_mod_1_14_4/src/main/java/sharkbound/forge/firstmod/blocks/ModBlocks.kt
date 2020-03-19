package sharkbound.forge.firstmod.blocks

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.entities.FirstBlockTileEntity
import sharkbound.forge.firstmod.items.FirstItem

@Mod.EventBusSubscriber
object ModBlocks {
    @ObjectHolder("$MOD_ID:firstblock")
    @JvmStatic
    lateinit var FIRST_BLOCK: FirstBlock


    @ObjectHolder("$MOD_ID:firstblock")
    @JvmStatic
    lateinit var FIRST_BLOCK_TILE_ENTITY: TileEntityType<FirstBlockTileEntity>
}

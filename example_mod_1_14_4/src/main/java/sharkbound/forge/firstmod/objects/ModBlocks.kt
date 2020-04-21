package sharkbound.forge.firstmod.objects

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.blocks.DuplicatorBlock
import sharkbound.forge.firstmod.blocks.MehBlock
import sharkbound.forge.firstmod.entities.DuplicatorBlockTileEntity
import sharkbound.forge.firstmod.entities.MehBlockTileEntity

@Mod.EventBusSubscriber
object ModBlocks {
    // meh block
    @ObjectHolder("$MOD_ID:${MehBlock.REGISTRY_NAME}")
    @JvmStatic
    lateinit var MEH: MehBlock

    @ObjectHolder("$MOD_ID:${MehBlock.REGISTRY_NAME}")
    @JvmStatic
    lateinit var MEH_TILE_ENTITY: TileEntityType<MehBlockTileEntity>

    // duplicator block
    @ObjectHolder("$MOD_ID:${DuplicatorBlock.REGISTRY_NAME}")
    @JvmStatic
    lateinit var DUPLICATOR: DuplicatorBlock

    @ObjectHolder("$MOD_ID:${DuplicatorBlock.REGISTRY_NAME}")
    @JvmStatic
    lateinit var DUPLICATOR_TILE_ENTITY: TileEntityType<DuplicatorBlockTileEntity>
}

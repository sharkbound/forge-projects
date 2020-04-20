package sharkbound.forge.firstmod.objects

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.blocks.DuplicatorBlock
import sharkbound.forge.firstmod.blocks.MehBlock
import sharkbound.forge.firstmod.entities.MehBlockItemEntity

@Mod.EventBusSubscriber
object ModBlocks {
    @ObjectHolder("$MOD_ID:${MehBlock.REGISTRY_NAME}")
    @JvmStatic
    lateinit var MEH: MehBlock

    @ObjectHolder("$MOD_ID:${MehBlock.REGISTRY_NAME}")
    @JvmStatic
    lateinit var MEH_BLOCK_TILE_ENTITY: TileEntityType<MehBlockItemEntity>

    @ObjectHolder("$MOD_ID:${DuplicatorBlock.REGISTRY_NAME}")
    @JvmStatic
    lateinit var DUPLICATOR: DuplicatorBlock
}

package sharkbound.forge.shared.util

import net.minecraft.block.Block
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType

fun <T : TileEntity> tileEntityRegistryBuilder(validBlocks: Array<Block>, factory: () -> T) =
        TileEntityType.Builder.create(factory, validBlocks)

fun <T : TileEntity> tileEntityRegistryBuilder(factory: () -> T, vararg validBlocks: Block) =
        TileEntityType.Builder.create(factory, validBlocks)
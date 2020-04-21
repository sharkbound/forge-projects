package sharkbound.forge.firstmod.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import sharkbound.forge.firstmod.objects.ModBlocks
import sharkbound.forge.firstmod.entities.MehBlockTileEntity
import sharkbound.forge.shared.extensions.destroyBlock
import sharkbound.forge.shared.extensions.isBlock

class MehBlock : Block(
        Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2f)
                .lightValue(14)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun hasTileEntity(state: BlockState?): Boolean {
        return true
    }

    override fun createTileEntity(state: BlockState?, world: IBlockReader?): TileEntity? {
        return MehBlockTileEntity()
    }

    fun destroyChain(pos: BlockPos, world: World) {
        if (pos.isBlock(world, ModBlocks.MEH)) {
            pos.destroyBlock(world)
            destroyChain(pos.east(), world)
            destroyChain(pos.west(), world)
            destroyChain(pos.north(), world)
            destroyChain(pos.south(), world)
            destroyChain(pos.up(), world)
            destroyChain(pos.down(), world)
        }
    }

    companion object {
        const val REGISTRY_NAME = "mehblock"
    }
}
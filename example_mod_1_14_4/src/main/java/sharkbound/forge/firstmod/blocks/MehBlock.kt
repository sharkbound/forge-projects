package sharkbound.forge.firstmod.blocks

import net.minecraft.block.*
import net.minecraft.block.material.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction
import net.minecraft.util.Hand
import net.minecraft.util.math.*
import net.minecraft.world.*
import sharkbound.commonutils.extensions.choice
import sharkbound.forge.firstmod.data.ModBlocks
import sharkbound.forge.firstmod.data.ModItems
import sharkbound.forge.firstmod.entities.MehBlockItemEntity
import sharkbound.forge.firstmod.items.MehWand
import sharkbound.forge.shared.extensions.*
import kotlin.contracts.ExperimentalContracts

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
        return MehBlockItemEntity()
    }

    fun destroyChain(pos: BlockPos, world: World) {
        if (pos.isBlock(world, ModBlocks.MEH_BLOCK)) {
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
package sharkbound.forge.firstmod.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import sharkbound.forge.firstmod.entities.DuplicatorBlockTileEntity
import sharkbound.forge.shared.extensions.isServer
import sharkbound.forge.shared.extensions.tileEntity
import kotlin.contracts.ExperimentalContracts

class DuplicatorBlock : Block(
        Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2f)
                .lightValue(0)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    @ExperimentalContracts
    override fun onBlockActivated(state: BlockState, worldIn: World, pos: BlockPos, player: PlayerEntity, handIn: Hand, hit: BlockRayTraceResult): Boolean {
        if (worldIn.isServer() && !player.isSneaking) {
            pos.tileEntity(worldIn)?.let {
                if (it is DuplicatorBlockTileEntity) {
                    player.openContainer(it)
                }
            }
        }
        return true
    }

    override fun hasTileEntity(state: BlockState?): Boolean {
        return true
    }

    override fun createTileEntity(state: BlockState?, world: IBlockReader?): TileEntity? {
        return DuplicatorBlockTileEntity()
    }

    companion object {
        const val REGISTRY_NAME = "duplicator"
    }
}
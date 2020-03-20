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
import sharkbound.forge.firstmod.entities.FirstBlockTileEntity
import sharkbound.forge.firstmod.interfaces.HasRegistryName
import sharkbound.forge.firstmod.items.FirstItem
import sharkbound.forge.shared.extensions.*
import kotlin.contracts.ExperimentalContracts

class FirstBlock : Block(
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
        return FirstBlockTileEntity()
    }

    val dirs = enumValues<Direction>()

    private fun destroyChain(pos: BlockPos, world: World) {
        if (pos.block(world) == ModBlocks.FIRST_BLOCK) {
            world.destroyBlock(pos, true)
            destroyChain(pos.east(), world)
            destroyChain(pos.west(), world)
            destroyChain(pos.north(), world)
            destroyChain(pos.south(), world)
            destroyChain(pos.up(), world)
            destroyChain(pos.down(), world)
        }
    }

    @ExperimentalContracts
    override fun onBlockActivated(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, handIn: Hand, hit: BlockRayTraceResult): Boolean {
        if (player.isServerPlayer() && world.isServerWorld() && player.mainHandItem itemIs ModItems.FIRST_ITEM) {
            pos.offset(dirs.choice()).let {
                when (FirstItem.getMode(player.mainHandItem)) {
                    FirstItem.Mode.DELETE -> world.destroyBlock(pos, true)
                    FirstItem.Mode.DUPLICATE -> world.setBlockState(it, ModBlocks.FIRST_BLOCK.defaultState)
                    FirstItem.Mode.DESTROY_CHAIN -> destroyChain(pos, world)
                }
            }
        }
        return false
    }

    companion object : HasRegistryName {
        override val REGISTRY_NAME: String
            get() = "firstblock"
    }
}
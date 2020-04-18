package sharkbound.forge.shared.extensions

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IWorld
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunk
import sharkbound.forge.shared.util.vec3D

const val BLOCK_UPDATE_AND_SEND_CHANGE = 1 or 2

val BlockPos.xd
    get() = x.toDouble()

val BlockPos.yd
    get() = y.toDouble()

val BlockPos.zd
    get() = z.toDouble()

val BlockPos.centerVec
    get() = Vec3d(x + .5, y.toDouble(), z + .5)

fun BlockPos.state(world: IWorld): BlockState =
        world.getBlockState(this)

fun BlockPos.block(world: IWorld): Block =
        world.getBlockState(this).block

fun BlockPos.chunk(world: IWorld): IChunk =
        world.getChunk(this)

fun BlockPos.destroyBlock(world: IWorld, drop: Boolean = false) {
    world.destroyBlock(this, drop)
}

fun BlockPos.setBlock(world: IWorld, block: Block) {
    world.setBlockState(this, block.defaultState, BLOCK_UPDATE_AND_SEND_CHANGE)
}

fun BlockPos.setBlock(world: IWorld, block: Block, flags: Int) {
    world.setBlockState(this, block.defaultState, flags)
}

fun BlockPos.setState(world: IWorld, state: BlockState) {
    world.setBlockState(this, state, BLOCK_UPDATE_AND_SEND_CHANGE)
}

fun BlockPos.setState(world: IWorld, state: BlockState, flags: Int) {
    world.setBlockState(this, state, flags)
}

fun BlockPos.setToAir(world: IWorld) {
    if (!world.isAirBlock(this)) {
        world.setBlockState(this, Blocks.AIR.defaultState, BLOCK_UPDATE_AND_SEND_CHANGE)
    }
}

fun BlockPos.isBlock(world: IWorld, block: Block): Boolean =
        block(world) == block

fun BlockPos.toVec3d(): Vec3d =
        vec3D(x, y, z)

fun BlockPos.isAir(world: IWorld): Boolean =
        world.isAirBlock(this)
package sharkbound.forge.shared.extensions

import net.minecraft.block.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunk
import sharkbound.forge.shared.util.vector

val BlockPos.xd
    get() = x.toDouble()

val BlockPos.yd
    get() = y.toDouble()

val BlockPos.zd
    get() = z.toDouble()

val BlockPos.centerVec
    get() = Vec3d(x + .5, y.toDouble(), z + .5)

fun BlockPos.state(world: World): BlockState =
        world.getBlockState(this)

fun BlockPos.block(world: World): Block =
        world.getBlockState(this).block

fun BlockPos.chunk(world: World): IChunk =
        world.getChunk(this)

fun BlockPos.destroyBlock(world: World, drop: Boolean = false) {
    world.destroyBlock(this, drop)
}

fun BlockPos.setBlock(world: World, block: Block) {
    world.setBlockState(this, block.defaultState)
}

fun BlockPos.setBlock(world: World, block: Block, flags: Int) {
    world.setBlockState(this, block.defaultState, flags)
}

fun BlockPos.setState(world: World, state: BlockState) {
    world.setBlockState(this, state)
}

fun BlockPos.setState(world: World, state: BlockState, flags: Int) {
    world.setBlockState(this, state, flags)
}

fun BlockPos.setToAir(world: World) {
    if (!world.isAirBlock(this)) {
        world.setBlockState(this, Blocks.AIR.defaultState)
    }
}

fun BlockPos.isBlock(world: World, block: Block): Boolean =
        block(world) == block

fun BlockPos.toVec3d(): Vec3d =
        vector(x, y, z)

fun BlockPos.isAir(world: World): Boolean =
        world.isAirBlock(this)
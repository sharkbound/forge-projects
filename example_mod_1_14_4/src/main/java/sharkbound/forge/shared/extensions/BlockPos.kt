package sharkbound.forge.shared.extensions

import net.minecraft.block.Block
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

val BlockPos.xd
    get() = x.toDouble()

val BlockPos.yd
    get() = y.toDouble()

val BlockPos.zd
    get() = z.toDouble()

val BlockPos.centerVec
    get() = Vec3d(x + .5, y.toDouble(), z + .5)

fun BlockPos.state(world: World) =
        world.getBlockState(this)

fun BlockPos.block(world: World): Block =
        world.getBlockState(this).block

fun BlockPos.chunk(world: World) =
        world.getChunk(this)
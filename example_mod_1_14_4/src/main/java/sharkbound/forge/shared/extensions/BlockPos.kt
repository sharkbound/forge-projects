package sharkbound.forge.shared.extensions

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

val BlockPos.xd
    get() = x.toDouble()

val BlockPos.yd
    get() = y.toDouble()

val BlockPos.zd
    get() = z.toDouble()

val BlockPos.centerVec
    get() = Vec3d(x + .5, y.toDouble(), z + .5)
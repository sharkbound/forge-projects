package sharkbound.forge.shared.extensions

import net.minecraft.util.math.*
import sharkbound.forge.shared.util.vec3D

fun Vec3d.toVec3I(): Vec3i =
        Vec3i(x, y, z)

fun Vec3d.mul(value: Double): Vec3d =
        mul(vec3D(value, value, value))

fun Vec3d.mul(value: Int): Vec3d =
        mul(value.toDouble())

fun Vec3d.toBlockPos(): BlockPos =
        BlockPos(this)
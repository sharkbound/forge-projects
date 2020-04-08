package sharkbound.forge.shared.extensions

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i
import sharkbound.forge.shared.util.vec3D
import kotlin.math.absoluteValue

fun Vec3d.toVec3I(): Vec3i =
        Vec3i(x, y, z)

fun Vec3d.mul(value: Double): Vec3d =
        mul(vec3D(value, value, value))

fun Vec3d.mul(value: Int): Vec3d =
        mul(value.toDouble())

fun Vec3d.toBlockPos(): BlockPos =
        BlockPos(this)

infix fun Vec3d.dist(other: Vec3d): Double =
        (x - other.x).absoluteValue + (y - other.y).absoluteValue + (z - other.z).absoluteValue

operator fun Vec3d.component1() =
        x

operator fun Vec3d.component2() =
        y

operator fun Vec3d.component3() =
        z
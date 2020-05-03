package sharkbound.forge.shared.extensions.vec3d

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i
import kotlin.math.absoluteValue
import kotlin.math.sqrt

fun Vec3d.toVec3I(): Vec3i =
        Vec3i(x, y, z)

fun Vec3d.magnitude() =
        sqrt(x * x + y * y + z * z)

fun Vec3d.toBlockPos(): BlockPos =
        BlockPos(this)

/**
 * source: https://forum.unity.com/threads/how-to-check-a-vector3-position-is-between-two-other-vector3-along-a-line.461474/
 */
fun Vec3d.isBetween(a: Vec3d, b: Vec3d): Boolean {
    return (b - a).normalize().dotProduct((this - b).normalize()) < 0 && (a - b).normalize().dotProduct((this - a).normalize()) < 0
}

infix fun Vec3d.dist(other: Vec3d): Double =
        (x - other.x).absoluteValue + (y - other.y).absoluteValue + (z - other.z).absoluteValue
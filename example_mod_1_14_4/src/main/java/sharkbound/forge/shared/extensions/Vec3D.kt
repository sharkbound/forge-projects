package sharkbound.forge.shared.extensions

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i
import sharkbound.forge.shared.util.Numbers
import sharkbound.forge.shared.util.vec3D
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.sqrt

///**
// * source: https://forum.unity.com/threads/how-to-check-a-vector3-position-is-between-two-other-vector3-along-a-line.461474/
// */
//fun Vec3d.isBetween(a: Vec3d, b: Vec3d): Boolean {
//    return (b - a).normalize().dotProduct((this - b).normalize()) < 0 && (a - b).normalize().dotProduct((this - a).normalize()) < 0
//}
//
///**
// * source: https://forum.unity.com/threads/how-to-check-a-vector3-position-is-between-two-other-vector3-along-a-line.461474/
// */
//fun Vec3d.project(onNormal: Vec3d): Vec3d {
//    val num = onNormal.dotProduct(onNormal)
//    return if (num < Numbers.EPSILON) Vec3d.ZERO else (onNormal * dotProduct(onNormal) / num)
//}

///**
// * source: https://forum.unity.com/threads/how-to-check-a-vector3-position-is-between-two-other-vector3-along-a-line.461474/
// *
// * [this] is the C in the equation
// */
//fun Vec3d.onSameLineAs(a: Vec3d, b: Vec3d, errorMargin: Double): Boolean {
//    /*
//    return Mathf.Approximately(
//        Vector3.Project( A-B , A-C ).magnitude ,
//        (A-B).magnitude
//    );
//    */
//    val abac = (a - b).project(a - this).magnitude()
//    val ab = (a - b).magnitude()
//    return abs(abac - ab) <= errorMargin
//}

fun Vec3d.magnitude() =
        sqrt(x * x + y * y + z * z)

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

operator fun Vec3d.component1(): Double =
        x

operator fun Vec3d.component2(): Double =
        y

operator fun Vec3d.component3(): Double =
        z

fun Vec3d.subtract(value: Double): Vec3d =
        subtract(value, value, value)

fun Vec3d.subtract(value: Int): Vec3d =
        subtract(value.toDouble(), value.toDouble(), value.toDouble())

fun Vec3d.add(value: Double): Vec3d =
        add(value, value, value)

fun Vec3d.add(value: Int): Vec3d =
        add(value.toDouble(), value.toDouble(), value.toDouble())

// - operator

operator fun Vec3d.minus(other: Vec3d): Vec3d =
        subtract(other)

operator fun Vec3d.minus(other: Double): Vec3d =
        subtract(other)

operator fun Vec3d.minus(other: Int): Vec3d =
        subtract(other)

// + operator

operator fun Vec3d.plus(other: Vec3d): Vec3d =
        add(other)

operator fun Vec3d.plus(other: Double): Vec3d =
        add(other)

operator fun Vec3d.plus(other: Int): Vec3d =
        add(other)

// * operator

operator fun Vec3d.times(other: Vec3d): Vec3d =
        mul(other)

operator fun Vec3d.times(other: Double): Vec3d =
        mul(other)

operator fun Vec3d.times(other: Int): Vec3d =
        mul(other)

// / operator

operator fun Vec3d.div(other: Vec3d): Vec3d =
        vec3D(x / other.x, y / other.y, z / other.z)

operator fun Vec3d.div(other: Double): Vec3d =
        vec3D(x / other, y / other, z / other)

operator fun Vec3d.div(other: Int): Vec3d =
        vec3D(x / other, y / other, z / other)




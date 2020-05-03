package sharkbound.forge.shared.extensions.vec3d

import net.minecraft.util.math.Vec3d
import sharkbound.forge.shared.util.vec3D
import kotlin.math.sign

fun Vec3d.turn(yaw: Float? = null, pitch: Float? = null): Vec3d {
    var ret = this
    yaw?.let { ret = rotateYaw(it) }
    pitch?.let { ret = rotatePitch(it) }
    return ret
}

fun Vec3d.up(amount: Double = 1.0): Vec3d =
        offset(y = amount)

fun Vec3d.up(amount: Int = 1): Vec3d =
        up(amount.toDouble())

fun Vec3d.up(amount: Float = 1f): Vec3d =
        up(amount.toDouble())

fun Vec3d.down(amount: Double = 1.0): Vec3d =
        offset(y = -amount)

fun Vec3d.down(amount: Int = 1): Vec3d =
        down(amount.toDouble())

fun Vec3d.down(amount: Float = 1f): Vec3d =
        down(amount.toDouble())

fun Vec3d.offset(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0): Vec3d =
        add(x, y, z)

fun Vec3d.offset(x: Int = 0, y: Int = 0, z: Int = 0): Vec3d =
        offset(x.toDouble(), y.toDouble(), z.toDouble())

fun Vec3d.offset(x: Float = 0f, y: Float = 0f, z: Float = 0f): Vec3d =
        offset(x.toDouble(), y.toDouble(), z.toDouble())
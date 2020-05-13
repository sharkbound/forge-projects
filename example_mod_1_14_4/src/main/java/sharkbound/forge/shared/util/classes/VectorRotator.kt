package sharkbound.forge.shared.util.classes

import net.minecraft.util.math.Vec3d
import sharkbound.forge.shared.extensions.vec3d.plus
import sharkbound.forge.shared.extensions.vec3d.times

// todo
@Deprecated("WIP")
class VectorRotator(val vec: Vec3d, rotationIn: Vec3d) {
    val rotation: Vec3d = rotationIn.normalize()

    fun pitch(pitch: Double) = pitch(pitch.toFloat())
    fun pitch(pitch: Float) = VectorRotator(vec, rotation.rotatePitch(pitch))

    fun yaw(yaw: Double) = yaw(yaw.toFloat())
    fun yaw(yaw: Float) = VectorRotator(vec, rotation.rotateYaw(yaw))

    fun forward(amount: Double) = VectorRotator(vec + rotation * amount, rotation)
}
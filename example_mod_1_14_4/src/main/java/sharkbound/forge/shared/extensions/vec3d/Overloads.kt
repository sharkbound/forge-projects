package sharkbound.forge.shared.extensions.vec3d

import net.minecraft.util.math.Vec3d
import sharkbound.forge.shared.util.vec3D

fun Vec3d.mul(value: Double): Vec3d =
        mul(vec3D(value, value, value))

fun Vec3d.mul(value: Int): Vec3d =
        mul(value.toDouble())

fun Vec3d.add(value: Double): Vec3d =
        add(value, value, value)

fun Vec3d.add(value: Int): Vec3d =
        add(value.toDouble(), value.toDouble(), value.toDouble())
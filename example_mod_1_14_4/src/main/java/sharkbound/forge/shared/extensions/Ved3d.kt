package sharkbound.forge.shared.extensions

import net.minecraft.util.math.Vec3d
import sharkbound.forge.shared.util.vector

fun Vec3d.mul(value: Double): Vec3d =
        mul(vector(value, value, value))

fun Vec3d.mul(value: Int): Vec3d =
        mul(value.toDouble())
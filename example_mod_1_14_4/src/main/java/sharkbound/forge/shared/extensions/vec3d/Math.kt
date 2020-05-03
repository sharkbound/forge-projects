package sharkbound.forge.shared.extensions.vec3d

import net.minecraft.util.math.Vec3d
import sharkbound.forge.shared.util.vec3D

operator fun Vec3d.minus(other: Vec3d): Vec3d =
        subtract(other)

operator fun Vec3d.minus(other: Double): Vec3d =
        subtract(other, other, other)

operator fun Vec3d.minus(other: Int): Vec3d =
        subtract(other.toDouble(), other.toDouble(), other.toDouble())

operator fun Vec3d.plus(other: Vec3d): Vec3d =
        add(other)

operator fun Vec3d.plus(other: Double): Vec3d =
        add(other)

operator fun Vec3d.plus(other: Int): Vec3d =
        add(other)

operator fun Vec3d.times(other: Vec3d): Vec3d =
        mul(other)

operator fun Vec3d.times(other: Double): Vec3d =
        mul(other)

operator fun Vec3d.times(other: Int): Vec3d =
        mul(other)

operator fun Vec3d.div(other: Vec3d): Vec3d =
        vec3D(x / other.x, y / other.y, z / other.z)

operator fun Vec3d.div(other: Double): Vec3d =
        vec3D(x / other, y / other, z / other)

operator fun Vec3d.div(other: Int): Vec3d =
        vec3D(x / other, y / other, z / other)

operator fun Vec3d.unaryMinus(): Vec3d =
        Vec3d(-x, -y, -z)
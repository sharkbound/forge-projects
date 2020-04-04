package sharkbound.forge.shared.util

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

fun vec3D(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0): Vec3d =
        Vec3d(x, y, z)

fun vec3D(x: Int = 0, y: Int = 0, z: Int = 0): Vec3d =
        Vec3d(x.toDouble(), y.toDouble(), z.toDouble())

fun vec3D(x: Long = 0, y: Long = 0, z: Long = 0): Vec3d =
        Vec3d(x.toDouble(), y.toDouble(), z.toDouble())

fun vec3D(x: Float = 0f, y: Float = 0f, z: Float = 0f): Vec3d =
        Vec3d(x.toDouble(), y.toDouble(), z.toDouble())

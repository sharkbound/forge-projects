package sharkbound.forge.shared.extensions

import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i

fun Vec3d.toVec3I(): Vec3i =
        Vec3i(x, y, z)
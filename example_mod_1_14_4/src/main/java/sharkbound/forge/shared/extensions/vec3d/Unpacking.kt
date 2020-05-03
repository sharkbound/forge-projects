package sharkbound.forge.shared.extensions.vec3d

import net.minecraft.util.math.Vec3d

operator fun Vec3d.component1(): Double =
        x

operator fun Vec3d.component2(): Double =
        y

operator fun Vec3d.component3(): Double =
        z
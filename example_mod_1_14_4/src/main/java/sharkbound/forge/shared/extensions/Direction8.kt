package sharkbound.forge.shared.extensions

import net.minecraft.util.Direction8
import net.minecraft.util.math.Vec3i

val Direction8.vec3I: Vec3i
    get() = directions.asSequence()
            .map { it.directionVec }
            .reduce { acc, it -> Vec3i(acc.x + it.x, acc.y + it.y, acc.z + it.z) }


package sharkbound.forge.shared.extensions

import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.util.math.RayTraceContext
import net.minecraft.util.math.Vec3d
import sharkbound.forge.shared.extensions.vec3d.mul
import sharkbound.forge.shared.extensions.vec3d.times

fun LivingEntity.rayTraceBlocks(
        distance: Double,
        blockMode: RayTraceContext.BlockMode = RayTraceContext.BlockMode.OUTLINE,
        fluidMode: RayTraceContext.FluidMode = RayTraceContext.FluidMode.SOURCE_ONLY,
        startVec: Vec3d? = null
): BlockRayTraceResult =
        run {
            val start = startVec ?: eyePos
            world.rayTraceBlocks(RayTraceContext(start, start.add(lookVec.times(distance)), blockMode, fluidMode, this))
        }
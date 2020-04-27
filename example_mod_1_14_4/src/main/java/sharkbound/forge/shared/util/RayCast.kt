package sharkbound.forge.shared.util

import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IWorld
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.eyePos
import sharkbound.forge.shared.extensions.plus
import sharkbound.forge.shared.extensions.times

inline fun <reified T : Entity> rayTraceEntities(world: IWorld, start: Vec3d, end: Vec3d) =
        world.entitiesInAABB<T>(AxisAlignedBB(start, end)).asSequence().filterNotNull()

inline fun <reified T : Entity> rayTraceEntities(entity: Entity, distance: Double): Sequence<T> {
    return entity.world.entitiesInAABB<T>(AxisAlignedBB(entity.eyePos, entity.eyePos + entity.lookVec.normalize() * distance))
            .asSequence().filterNotNull().filter { it != entity }
}
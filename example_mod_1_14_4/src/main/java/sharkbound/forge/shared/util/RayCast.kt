package sharkbound.forge.shared.util

import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IWorld
import sharkbound.forge.shared.extensions.vec3d.dist
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.eyePos
import sharkbound.forge.shared.extensions.vec3d.minus
import sharkbound.forge.shared.extensions.vec3d.plus
import sharkbound.forge.shared.extensions.vec3d.times
import kotlin.contracts.ExperimentalContracts

@ExperimentalContracts
inline fun <reified T : Entity> rayTraceEntities(world: IWorld, start: Vec3d, endIn: Vec3d): Sequence<T> = sequence {
    val dir = (endIn - start).normalize() * .5
    val end = endIn + dir
    var rayPos = start
    var dist = start dist end
    val startToEndDist = dist

    val entities = world.entitiesInAABB<T>(AxisAlignedBB(start, end)).asSequence().filterNotNull().toMutableList()
    if (entities.isEmpty()) return@sequence

    while (true) {
        val ents = entities.filter { rayPos in it.boundingBox }
        entities.removeAll(ents)
        yieldAll(ents)

        rayPos += dir
        dist = rayPos dist end

        if (dist > startToEndDist || entities.isEmpty()) return@sequence
    }
}

@ExperimentalContracts
inline fun <reified T : Entity> rayTraceEntities(entity: Entity, distance: Double): Sequence<T> {
    return rayTraceEntities<T>(entity.world, entity.eyePos, entity.eyePos + entity.lookVec.normalize() * distance).filter { it != entity }
}

package sharkbound.forge.shared.util

import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import sharkbound.forge.shared.extensions.pos

// min max
fun createAABB(min: Vec3d, max: Vec3d): AxisAlignedBB =
        AxisAlignedBB(min, max)

fun createAABB(min: BlockPos, max: BlockPos): AxisAlignedBB =
        AxisAlignedBB(min, max)

// vec3d
fun createAABB(center: Vec3d, sizeX: Double = 0.0, sizeY: Double = 0.0, sizeZ: Double = 0.0): AxisAlignedBB =
        AxisAlignedBB(center, center).grow(sizeX, sizeY, sizeZ)

fun createAABB(center: Vec3d, sizeX: Int = 0, sizeY: Int = 0, sizeZ: Int = 0): AxisAlignedBB =
        createAABB(center, sizeX.toDouble(), sizeY.toDouble(), sizeZ.toDouble())

// block pos
fun createAABB(center: BlockPos, sizeX: Double = 0.0, sizeY: Double = 0.0, sizeZ: Double = 0.0): AxisAlignedBB =
        AxisAlignedBB(center, center).grow(sizeX, sizeY, sizeZ)

fun createAABB(center: BlockPos, sizeX: Int = 0, sizeY: Int = 0, sizeZ: Int = 0): AxisAlignedBB =
        createAABB(center, sizeX.toDouble(), sizeY.toDouble(), sizeZ.toDouble())

// entities
fun createAABB(entity: Entity, sizeX: Int = 0, sizeY: Int = 0, sizeZ: Int = 0): AxisAlignedBB =
        createAABB(entity.positionVec, sizeX.toDouble(), sizeY.toDouble(), sizeZ.toDouble())

fun createAABB(entity: Entity, sizeX: Double = 0.0, sizeY: Double = 0.0, sizeZ: Double = 0.0): AxisAlignedBB =
        AxisAlignedBB(entity.positionVec, entity.positionVec).grow(sizeX, sizeY, sizeZ)
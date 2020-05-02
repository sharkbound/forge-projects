package sharkbound.forge.shared.util

import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

fun createArrow(world: World, pos: Vec3d): ArrowEntity =
        ArrowEntity(world, pos.x, pos.y, pos.z)

fun createArrow(world: World, x: Double, y: Double, z: Double): ArrowEntity =
        createArrow(world, Vec3d(x, y, z))
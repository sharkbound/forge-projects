package sharkbound.forge.shared.extensions

import net.minecraft.block.BlockState
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.effect.LightningBoltEntity
import net.minecraft.entity.item.FallingBlockEntity
import net.minecraft.particles.IParticleData
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IWorld
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
fun World?.isServerWorld(): Boolean {
    contract {
        returns(true) implies (this@isServerWorld is ServerWorld)
    }
    return this is ServerWorld
}

@ExperimentalContracts
fun World?.isClientWorld(): Boolean {
    contract {
        returns(true) implies (this@isClientWorld is ClientWorld)
    }
    return this is ClientWorld
}

@ExperimentalContracts
fun <T : IParticleData> World.particle(type: T, x: Double, y: Double, z: Double, count: Int = 1, offX: Double = 0.0, offY: Double = 0.0, offZ: Double = 0.0, speed: Double = 0.0): Boolean {
    if (this.isServerWorld()) {
        spawnParticle(type, x, y, z, count, offX, offY, offZ, speed)
        return true
    }
    return false
}

@ExperimentalContracts
fun <T : IParticleData> World.particle(type: T, x: Int, y: Int, z: Int, count: Int = 1, offX: Double = 0.0, offY: Double = 0.0, offZ: Double = 0.0, speed: Double = 0.0): Boolean =
        particle(type, x.toDouble(), y.toDouble(), z.toDouble(), count, offX, offY, offZ, speed)

fun ServerWorld.doLightningStrike(pos: Vec3d, effectOnly: Boolean = false) {
    addLightningBolt(LightningBoltEntity(this, pos.x, pos.y, pos.z, effectOnly))
}

fun ServerWorld.addFallingBlock(pos: Vec3d, blockState: BlockState, velocity: Vec3d = Vec3d.ZERO, gravity: Boolean = true): FallingBlockEntity =
        FallingBlockEntity(this, pos.x, pos.y, pos.z, blockState).also {
            it.setNoGravity(!gravity)
            it.setVel(velocity)
            it.fallTime = 1
            addEntity(it)
        }

inline fun <reified T : Entity> IWorld.entitiesInAABB(aabb: AxisAlignedBB) =
        world.getEntitiesWithinAABB(T::class.java, aabb)
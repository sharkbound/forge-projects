package sharkbound.forge.shared.extensions

import net.minecraft.client.world.ClientWorld
import net.minecraft.particles.IParticleData
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
package sharkbound.forge.shared.extensions

import net.minecraft.client.world.ClientWorld
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
fun World.isServerWorld(): Boolean {
    contract {
        returns(true) implies (this@isServerWorld is ServerWorld)
    }
    return this is ServerWorld
}

@ExperimentalContracts
fun World.isClientWorld(): Boolean {
    contract {
        returns(true) implies (this@isClientWorld is ClientWorld)
    }
    return this is ClientWorld
}
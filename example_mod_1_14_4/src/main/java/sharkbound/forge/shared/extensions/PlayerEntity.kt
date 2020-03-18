package sharkbound.forge.shared.extensions

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
fun Entity?.isServerPlayer(): Boolean {
    contract {
        returns(true) implies (this@isServerPlayer is ServerPlayerEntity)
    }
    return this is ServerPlayerEntity
}

@ExperimentalContracts
inline infix fun Entity?.ifServerPlayer(block: ServerPlayerEntity.() -> Unit) {
    if (this.isServerPlayer()) {
        block()
    }
}
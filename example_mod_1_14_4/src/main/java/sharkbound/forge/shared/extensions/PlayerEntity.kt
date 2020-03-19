package sharkbound.forge.shared.extensions

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
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

val PlayerEntity.mainHandItem
    get() = getHeldItem(Hand.MAIN_HAND)
val PlayerEntity.offHandItem
    get() = getHeldItem(Hand.OFF_HAND)

data class HeldItemInfo(val hand: Hand, val stack: ItemStack, val player: PlayerEntity) {
    @ExperimentalContracts
    inline fun <reified T : Item> isType(): Boolean =
            stack.isType<T>()

    inline fun <reified T : Item> asType(): T? =
            item as? T

    val isEmpty by lazy { stack.isEmpty }
    val item by lazy { stack.item }
}

val PlayerEntity.heldItemInfo: HeldItemInfo
    get() {
        val hand = when {
            isHandActive -> activeHand
            !mainHandItem.isEmpty -> Hand.MAIN_HAND
            !offHandItem.isEmpty -> Hand.OFF_HAND
            else -> Hand.MAIN_HAND
        }

        return HeldItemInfo(hand, getHeldItem(hand), this)
    }
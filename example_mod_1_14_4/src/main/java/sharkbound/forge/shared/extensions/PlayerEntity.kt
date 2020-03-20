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

class HeldItemInfo(val hand: Hand, val stack: ItemStack, val player: PlayerEntity) {
    val isEmpty: Boolean = stack.isEmpty
    val item: Item = stack.item

    infix fun itemIs(other: Item): Boolean =
            item == other

    override fun equals(other: Any?): Boolean =
            when (other) {
                is Item -> item == other
                is ItemStack -> item == other.item
                is HeldItemInfo -> item == other.item
                else -> false
            }

    override fun hashCode(): Int {
        var result = isEmpty.hashCode()
        result = 31 * result + item.hashCode()
        return result
    }

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
package sharkbound.forge.shared.extensions

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
inline fun <reified T : Item> ItemStack.isType(): Boolean =
        item is T

inline fun <reified T : Item> ItemStack.itemAs(): T? =
        item as? T
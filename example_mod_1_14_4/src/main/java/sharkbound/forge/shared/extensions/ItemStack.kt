package sharkbound.forge.shared.extensions

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

inline fun <reified T : Item> ItemStack.isType(): Boolean =
        item is T

inline fun <reified T : Item> ItemStack.itemAs(): T? =
        item as? T
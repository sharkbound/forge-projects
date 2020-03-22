package sharkbound.forge.shared.extensions

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

infix fun ItemStack.isItem(other: Item): Boolean =
        item == other

infix fun ItemStack.isNotItem(other: Item): Boolean =
        item != other
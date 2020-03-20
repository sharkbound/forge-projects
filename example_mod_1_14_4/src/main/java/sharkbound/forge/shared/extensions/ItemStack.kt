package sharkbound.forge.shared.extensions

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

infix fun ItemStack.itemIs(other: Item): Boolean =
        item == other
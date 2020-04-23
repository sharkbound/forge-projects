package sharkbound.forge.shared.extensions

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.ActionResult
import net.minecraft.util.ActionResultType

infix fun ItemStack.isItem(other: Item): Boolean =
        item == other

infix fun ItemStack.isNotItem(other: Item): Boolean =
        item != other

fun ItemStack.toActionResult(result: ActionResultType): ActionResult<ItemStack> =
        ActionResult(result, this)

inline infix fun ItemStack.modifyNBT(block: CompoundNBT.() -> Unit) {
    orCreateTag.block()
}

infix fun ItemStack?.hasNBTKey(key: String): Boolean =
        this?.tag?.contains(key) == true

val ItemStack?.name: String
    get() = when {
        this == null -> ""
        hasDisplayName() -> displayName.formattedText
        else -> item.name.formattedText
    }

infix fun ItemStack?.areEqual(other: ItemStack?): Boolean {
    return ItemStack.areItemStacksEqual(this ?: return false, other ?: return false)
}

infix fun ItemStack?.areEqualIgnoreDurability(other: ItemStack?): Boolean {
    return ItemStack.areItemsEqualIgnoreDurability(this ?: return false, other ?: return false)
}

infix fun ItemStack?.areTagsEqual(other: ItemStack?): Boolean {
    return ItemStack.areItemStackTagsEqual(this ?: return false, other ?: return false)
}

infix fun ItemStack?.areItemsEqual(other: ItemStack?): Boolean {
    return ItemStack.areItemsEqual(this ?: return false, other ?: return false)
}

val ItemStack?.freeAmount
    get() = this?.run { maxStackSize - count } ?: 0
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
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
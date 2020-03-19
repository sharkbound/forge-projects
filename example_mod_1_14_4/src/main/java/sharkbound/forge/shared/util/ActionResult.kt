package sharkbound.forge.shared.util

import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.ActionResultType

fun emptyStackResult(result: ActionResultType): ActionResult<ItemStack> =
        ActionResult(result, ItemStack.EMPTY)
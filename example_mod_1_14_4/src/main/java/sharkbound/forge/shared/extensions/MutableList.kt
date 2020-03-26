package sharkbound.forge.shared.extensions

import net.minecraft.util.text.ITextComponent
import sharkbound.forge.shared.util.toText

fun MutableList<ITextComponent>.addAll(vararg values: String) =
        apply {
            addAll(values.map(::toText))
        }
package sharkbound.forge.shared.extensions

import net.minecraft.util.text.StringTextComponent
import sharkbound.forge.shared.util.toText

const val MAGIC_SYMBOL = '§'

fun String.color(altChar: Char = '&') =
        replace(altChar, MAGIC_SYMBOL)

fun color(string: String) =
        string.color()

fun String.toText(): StringTextComponent =
        toText(this)
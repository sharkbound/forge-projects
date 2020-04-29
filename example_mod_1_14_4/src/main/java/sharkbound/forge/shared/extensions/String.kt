package sharkbound.forge.shared.extensions

import net.minecraft.util.text.StringTextComponent
import sharkbound.forge.shared.util.asText

const val MAGIC_SYMBOL = '§'

fun String.color(altChar: Char = '&') =
        replace(altChar, MAGIC_SYMBOL)

fun color(string: String) =
        string.color()

fun String.asText(): StringTextComponent =
        asText(this)
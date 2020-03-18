package sharkbound.forge.shared.extensions

const val MAGIC_SYMBOL = '§'

fun String.color(altChar: Char = '&') =
        replace(altChar, MAGIC_SYMBOL)

fun color(string: String) =
        string.color()
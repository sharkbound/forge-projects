package sharkbound.forge.shared.util

import net.minecraft.util.text.StringTextComponent
import sharkbound.forge.shared.extensions.color

fun toText(string: String): StringTextComponent =
        StringTextComponent(string.color())
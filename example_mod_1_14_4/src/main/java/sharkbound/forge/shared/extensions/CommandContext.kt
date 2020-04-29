package sharkbound.forge.shared.extensions

import com.mojang.brigadier.context.CommandContext
import net.minecraft.command.CommandSource
import sharkbound.forge.shared.util.asText

fun CommandContext<CommandSource>.send(obj: Any?, altColorChar: Char = '&', logging: Boolean = false) {
    val string = obj.toString().color(altColorChar)
    source.sendFeedback(asText(string), logging)
}
package sharkbound.forge.shared.extensions

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.text.StringTextComponent

fun ServerPlayerEntity.send(obj: Any?, altChar: Char = '&') {
    sendMessage(StringTextComponent(obj.toString().color(altChar)))
}
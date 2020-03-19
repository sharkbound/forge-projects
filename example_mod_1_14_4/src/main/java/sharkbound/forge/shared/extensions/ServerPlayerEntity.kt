package sharkbound.forge.shared.extensions

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.text.StringTextComponent

fun PlayerEntity.send(obj: Any?, altChar: Char = '&') {
    sendMessage(StringTextComponent(obj.toString().color(altChar)))
}
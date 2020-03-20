package sharkbound.forge.shared.extensions

import net.minecraft.entity.Entity
import net.minecraft.util.text.StringTextComponent

fun Entity.setInAir() {
    isAirBorne = true
    onGround = false
}

fun Entity.teleportRelative(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    teleportKeepLoaded(posX + x, posY + y, posZ + z)
}

fun Entity.send(obj: Any?, altChar: Char = '&') {
    sendMessage(StringTextComponent(obj.toString().color(altChar)))
}
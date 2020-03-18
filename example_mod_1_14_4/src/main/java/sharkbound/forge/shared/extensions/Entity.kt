package sharkbound.forge.shared.extensions

import net.minecraft.entity.Entity

fun Entity.setInAir() {
    isAirBorne = true
    onGround = false
}
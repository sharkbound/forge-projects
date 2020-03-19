package sharkbound.forge.shared.extensions

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.Hand

val ServerPlayerEntity.mainHandItem
    get() = getHeldItem(Hand.MAIN_HAND)

val ServerPlayerEntity.offHandItem
    get() = getHeldItem(Hand.OFF_HAND)
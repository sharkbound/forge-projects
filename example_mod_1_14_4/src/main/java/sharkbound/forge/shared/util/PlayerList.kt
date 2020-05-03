package sharkbound.forge.shared.util

import net.minecraft.entity.player.ServerPlayerEntity
import sharkbound.forge.shared.extensions.nameOrCustomLower

fun findPlayer(partialName: String): ServerPlayerEntity? {
    val search = partialName.toLowerCase()
    return playerList.players.firstOrNull { search in it.nameOrCustomLower }
}
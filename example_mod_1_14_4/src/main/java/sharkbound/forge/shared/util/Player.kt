package sharkbound.forge.shared.util

import net.minecraft.entity.player.ServerPlayerEntity
import java.util.*

fun getPlayer(username: String): ServerPlayerEntity? =
        server.playerList.getPlayerByUsername(username)

fun getPlayer(playerId: UUID): ServerPlayerEntity? =
        server.playerList.getPlayerByUUID(playerId)

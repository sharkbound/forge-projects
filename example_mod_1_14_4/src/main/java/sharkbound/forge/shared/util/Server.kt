package sharkbound.forge.shared.util

import net.minecraft.server.integrated.IntegratedServer
import net.minecraft.server.management.PlayerList
import sharkbound.forge.firstmod.objects.minecraft

val server: IntegratedServer
    get() = minecraft.integratedServer ?: error("[ERROR] failed to get integrated server as it was null")

val playerList: PlayerList
    get() = server.playerList

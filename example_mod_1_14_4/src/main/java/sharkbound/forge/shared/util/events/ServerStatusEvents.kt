package sharkbound.forge.shared.util.events

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.server.integrated.IntegratedServer
import net.minecraft.server.management.PlayerList
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.server.FMLServerStartedEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent
import sharkbound.forge.firstmod.objects.minecraft
import sharkbound.forge.shared.extensions.nameOrCustomLower

enum class ServerState {
    STARTING,
    STARTED,
    STOPPING,
    STOPPED,
    NONE
}

object Server {
    var state = ServerState.NONE
    val players get() = server.playerList.players.asSequence()
    val playerList get() = server.playerList

    fun findPlayer(partialName: String): ServerPlayerEntity? {
        val search = partialName.toLowerCase()
        return players.firstOrNull { search in it.nameOrCustomLower }
    }
}

@Mod.EventBusSubscriber
object ServerStatusEvents {
    @SubscribeEvent
    @JvmStatic
    fun onServerStarting(e: FMLServerStartingEvent) {
        Server.state = ServerState.STARTING
    }

    @SubscribeEvent
    @JvmStatic
    fun onServerStarted(e: FMLServerStartedEvent) {
        Server.state = ServerState.STARTED
    }

    @SubscribeEvent
    @JvmStatic
    fun onServerStopping(e: FMLServerStoppingEvent) {
        Server.state = ServerState.STOPPING
    }

    @SubscribeEvent
    @JvmStatic
    fun onServerStopped(e: FMLServerStoppedEvent) {
        Server.state = ServerState.STOPPED
    }
}

val server: IntegratedServer
    get() = minecraft.integratedServer ?: error("[ERROR] failed to get integrated server as it was null")

val serverOrNull: IntegratedServer?
    get() = minecraft.integratedServer

val playerList: PlayerList
    get() = server.playerList
package sharkbound.forge.firstmod.commands

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands
import net.minecraft.entity.player.PlayerEntity
import sharkbound.forge.shared.extensions.send
import java.util.*

object GodCommand {
    private val godPlayers = mutableSetOf<UUID>()

    fun isInGod(player: PlayerEntity): Boolean =
            player.uniqueID in godPlayers

    fun register(dis: CommandDispatcher<CommandSource>) {
        dis.register(Commands.literal("god").executes {
            it.source.asPlayer().let { player ->
                player.send(when (player.uniqueID) {
                    in godPlayers -> {
                        godPlayers.remove(player.uniqueID)
                        "&eyou disabled godmode"
                    }
                    else -> {
                        godPlayers.add(player.uniqueID)
                        "&eyou enabled godmode"
                    }
                })
                0
            }
        })
    }
}
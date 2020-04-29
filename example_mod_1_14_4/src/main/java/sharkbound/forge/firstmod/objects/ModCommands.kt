package sharkbound.forge.firstmod.objects

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands
import net.minecraft.network.play.server.STitlePacket
import sharkbound.forge.firstmod.commands.FlyCommand
import sharkbound.forge.firstmod.commands.MarkWandCommand
import sharkbound.forge.firstmod.commands.WalkCommand
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.asText
import kotlin.contracts.ExperimentalContracts

object ModCommands {
    @ExperimentalContracts
    fun register(dis: CommandDispatcher<CommandSource>) {
        FlyCommand.register(dis)
        WalkCommand.register(dis)
        MarkWandCommand.register(dis)
        dis.register(Commands.literal("titletest").executes { s ->
            s.source.world.let { w ->
                val player = s.source.asPlayer()
                if (w.isServerWorld()) {
                    player.connection.sendPacket(STitlePacket(STitlePacket.Type.TITLE, "&ehello main title".asText(), 60, 80, 60))
                    player.connection.sendPacket(STitlePacket(STitlePacket.Type.SUBTITLE, "&7hello subtitle".asText(), 60, 80, 60))
                    player.connection.sendPacket(STitlePacket(STitlePacket.Type.ACTIONBAR, "&4hello action bar".asText(), 60, 80, 60))
                }
            }
            0
        })
    }
}
package sharkbound.forge.firstmod.commands

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands
import net.minecraft.network.play.server.STitlePacket
import sharkbound.forge.shared.extensions.asText
import sharkbound.forge.shared.extensions.isServerWorld
import kotlin.contracts.ExperimentalContracts

object ModCommands {
    @ExperimentalContracts
    fun register(dis: CommandDispatcher<CommandSource>) {
        FlyCommand.register(dis)
        WalkCommand.register(dis)
        MarkWandCommand.register(dis)
        HealCommand.register(dis)
        GodCommand.register(dis)
        ToggleCommand.register(dis)
        registerTitleTest(dis)
    }

    @ExperimentalContracts
    private fun registerTitleTest(dis: CommandDispatcher<CommandSource>) {
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
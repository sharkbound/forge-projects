package sharkbound.forge.firstmod.commands

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands
import sharkbound.forge.shared.extensions.color
import sharkbound.forge.shared.extensions.send

object ToggleCommand {
    var zombiesThrowBlocks = false

    fun register(dis: CommandDispatcher<CommandSource>) {
        dis.register(Commands.literal("toggle")
                .then(Commands.literal("zombiesThrowBlocks").executes {
                    zombiesThrowBlocks = !zombiesThrowBlocks
                    it.send((if (zombiesThrowBlocks) "&eenabled zombies throwing blocks" else "&edisabled zombies throwing blocks").color())
                    0
                })
                .then(Commands.literal("fly").executes {
                    val player = it.source.asPlayer()
                    player.abilities.run {
                        allowFlying = !allowFlying
                        isFlying = allowFlying
                        player.send("&e${if (allowFlying) "enabled" else "disabled"} flying for you")
                    }
                    player.sendPlayerAbilities()
                    0
                })
        )
    }
}

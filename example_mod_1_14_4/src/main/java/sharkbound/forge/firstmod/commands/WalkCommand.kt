package sharkbound.forge.firstmod.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.FloatArgumentType
import com.mojang.brigadier.context.CommandContext
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands
import sharkbound.forge.shared.extensions.send

object WalkCommand {
    //    found on a tutorial, i am not using this way myself, at least as of now
    fun register(dis: CommandDispatcher<CommandSource>) {
        dis.register(Commands.literal("walk")
                .then(Commands.argument(WALK_SPEED_ARGUMENT, FloatArgumentType.floatArg(0f, 10f))
                        .executes {
                            it?.let {
                                execute(it, FloatArgumentType.getFloat(it, WALK_SPEED_ARGUMENT))
                            }
                            0
                        }
                )
        )
    }

    private const val WALK_SPEED_ARGUMENT = "walkspeed"

    private fun execute(c: CommandContext<CommandSource>, speed: Float) {
        c.source.asPlayer().abilities.walkSpeed = speed
        c.source.asPlayer().sendPlayerAbilities()
        c.send("&aset your walk speed to &e$speed")
    }
}
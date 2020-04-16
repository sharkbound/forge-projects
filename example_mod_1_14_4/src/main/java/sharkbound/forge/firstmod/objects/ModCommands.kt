package sharkbound.forge.firstmod.objects

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.command.CommandSource
import sharkbound.forge.firstmod.commands.FlyCommand
import sharkbound.forge.firstmod.commands.WalkCommand

object ModCommands {
    fun register(dis: CommandDispatcher<CommandSource>) {
        FlyCommand.register(dis)
        WalkCommand.register(dis)
    }
}
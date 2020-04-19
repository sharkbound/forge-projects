package sharkbound.forge.firstmod.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.FloatArgumentType
import com.mojang.brigadier.context.CommandContext
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands
import sharkbound.forge.firstmod.objects.Flags
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.name
import sharkbound.forge.shared.extensions.send

object MarkWandCommand {
    //    found on a tutorial, i am not using this way myself, at least as of now
    fun register(dis: CommandDispatcher<CommandSource>) {
        dis.register(Commands.literal("markwand")
                .then(Commands.literal("glass_box_spawner").executes {
                    val player = it.source.asPlayer()
                    if (player.item.isEmpty) {
                        player.send("you must be holding a item to mark!")
                        return@executes 0
                    }
                    player.item.stack.orCreateTag.putByte(Flags.DUNGEON_SPAWNER_KEY, 0)
                    player.send("marked ${player.item.stack.name}")
                    0
                })
        )
    }
}
package sharkbound.forge.firstmod.commands

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands
import net.minecraft.entity.player.ServerPlayerEntity
import sharkbound.forge.firstmod.objects.Flags
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.name
import sharkbound.forge.shared.extensions.send

object MarkWandCommand {
    private fun mark(player: ServerPlayerEntity, nbtKey: String): Int {
        if (player.item.isEmpty) {
            player.send("you must be holding a item to mark!")
            return 0
        }
        player.item.stack.orCreateTag.putByte(nbtKey, 0)
        player.send("marked ${player.item.stack.name}")
        return 0
    }

    //    found on a tutorial, i am not using this way myself, at least as of now
    fun register(dis: CommandDispatcher<CommandSource>) {
        dis.register(Commands.literal("markwand")
                .then(Commands.literal("glass_box_spawner").executes {
                    mark(it.source.asPlayer(), Flags.GLASS_BOX_SPAWNER)
                }).then(Commands.literal("arrow_spawner").executes {
                    mark(it.source.asPlayer(), Flags.ARROW_SPAWNER_KEY)
                }).then(Commands.literal("death_beam").executes {
                    mark(it.source.asPlayer(), Flags.DEATH_BEAM)
                })

        )
    }
}
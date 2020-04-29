package sharkbound.forge.firstmod.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.FloatArgumentType
import com.mojang.brigadier.context.CommandContext
import net.minecraft.command.CommandSource
import net.minecraft.command.Commands
import sharkbound.forge.shared.extensions.send

object HealCommand {
    //    found on a tutorial, i am not using this way myself, at least as of now
    fun register(dis: CommandDispatcher<CommandSource>) {
        dis.register(Commands.literal("heal").executes {
            it.source.asPlayer().let { player ->
                player.health = player.maxHealth
                player.foodStats.run {
                    foodLevel = 20
                    setFoodSaturationLevel(20f)
                }
                player.send("&dyou have been healed!")
                0
            }
        })
    }
}
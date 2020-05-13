package sharkbound.forge.firstmod.events.toggleables

import net.minecraft.block.Blocks
import net.minecraft.entity.item.FallingBlockEntity
import net.minecraft.entity.item.TNTEntity
import net.minecraft.entity.monster.ZombieEntity
import net.minecraft.util.math.Vec3d
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.commands.ToggleCommand
import sharkbound.forge.firstmod.events.scheduler.TickHandlerServerEvents
import sharkbound.forge.shared.extensions.addFallingBlock
import sharkbound.forge.shared.extensions.asServerPlayer
import sharkbound.forge.shared.extensions.asServerWorld
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.pos
import sharkbound.forge.shared.extensions.setVel
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.server
import sharkbound.forge.shared.util.vec3D

@Mod.EventBusSubscriber
object ToggleableFeatureEvents {
    @SubscribeEvent
    @JvmStatic
    fun zombieThrowingBlocksPlayerTick(e: TickEvent.PlayerTickEvent) {
        if (!ToggleCommand.zombiesThrowBlocks || !TickHandlerServerEvents.serverRunning) return
        val player = e.player.asServerPlayer() ?: return
        val world = player.world.asServerWorld() ?: return

        if (server.tickCounter % 20 != 0) return
        world.entitiesInAABB<ZombieEntity>(player.boundingBox.grow(15.0)).forEach {
            world.addFallingBlock(it.pos, Blocks.OBSIDIAN.defaultState, vec3D(y = 3.2))
        }
    }
}
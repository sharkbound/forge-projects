package sharkbound.forge.firstmod.events.toggleables

import net.minecraft.block.Blocks
import net.minecraft.entity.monster.ZombieEntity
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.commonutils.rand
import sharkbound.commonutils.util.randBoolean
import sharkbound.forge.firstmod.commands.ToggleCommand
import sharkbound.forge.shared.extensions.addFallingBlock
import sharkbound.forge.shared.extensions.asServerPlayer
import sharkbound.forge.shared.extensions.asServerWorld
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.pos
import sharkbound.forge.shared.extensions.vec3d.minus
import sharkbound.forge.shared.extensions.vec3d.times
import sharkbound.forge.shared.util.events.Server
import sharkbound.forge.shared.util.events.ServerState
import sharkbound.forge.shared.util.events.server
import sharkbound.forge.shared.util.vec3D

@Mod.EventBusSubscriber
object ToggleableFeatureEvents {
    @SubscribeEvent
    @JvmStatic
    fun zombieThrowingBlocksPlayerTick(e: TickEvent.PlayerTickEvent) {
        if (!ToggleCommand.zombiesThrowBlocks || Server.state != ServerState.STARTED) return
        if (server.tickCounter % 20 != 0) return
        val player = e.player.asServerPlayer() ?: return
        val world = player.world.asServerWorld() ?: return

        world.entitiesInAABB<ZombieEntity>(player.boundingBox.grow(15.0)).forEach {
            if (rand.nextFloat() < .3) {
                val vel = (player.pos - it.pos) * .022
                world.addFallingBlock(it.pos, Blocks.OBSIDIAN, vec3D(vel.x, 3.2, vel.z))
            }
        }
    }
}
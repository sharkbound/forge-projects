package sharkbound.forge.firstmod.events

import net.minecraft.block.Blocks
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MobEntity
import net.minecraft.network.play.server.SRemoveEntityEffectPacket
import net.minecraft.potion.Effects
import net.minecraft.util.math.EntityRayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.networking.Network
import sharkbound.forge.firstmod.objects.minecraft
import sharkbound.forge.shared.extensions.block
import sharkbound.forge.shared.extensions.dist
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.eyePos
import sharkbound.forge.shared.extensions.instance
import sharkbound.forge.shared.extensions.isAir
import sharkbound.forge.shared.extensions.isBetween
import sharkbound.forge.shared.extensions.isClient
import sharkbound.forge.shared.extensions.isLivingEntity
import sharkbound.forge.shared.extensions.isServer
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.livingEntities
import sharkbound.forge.shared.extensions.minus
import sharkbound.forge.shared.extensions.mul
import sharkbound.forge.shared.extensions.onSameLineAs
import sharkbound.forge.shared.extensions.plus
import sharkbound.forge.shared.extensions.pos
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.send
import sharkbound.forge.shared.extensions.setBlock
import sharkbound.forge.shared.extensions.times
import sharkbound.forge.shared.extensions.toBlockPos
import sharkbound.forge.shared.extensions.toVec3I
import sharkbound.forge.shared.extensions.toVec3d
import sharkbound.forge.shared.util.createAABBSquare
import sharkbound.forge.shared.util.playerList
import kotlin.contracts.ExperimentalContracts
import kotlin.streams.asSequence

@Mod.EventBusSubscriber
object ClientEvents {
    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun serverTick(e: TickEvent.ServerTickEvent) {
//        val player = playerList.players.firstOrNull() ?: return
//        val world = player.world as? ServerWorld ?: return
//
//        val a = player.eyePos
//        val b = player.rayTraceBlocks(10.0).pos.toVec3d()
//
//        for (entity in world.livingEntities()) {
//            if (entity != player && entity.pos.onSameLineAs(a, b, 0.03)) {
//                entity.addPotionEffect(Effects.GLOWING.instance(2))
////                entity.health = 0f
//            }
//        }
    }
}
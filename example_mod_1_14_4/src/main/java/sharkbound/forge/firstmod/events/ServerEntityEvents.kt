package sharkbound.forge.firstmod.events

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MobEntity
import net.minecraft.entity.ai.goal.FollowOwnerGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.monster.CreeperEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.potion.Effects
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.entities.goals.CreeperFollowPlayerGoal
import sharkbound.forge.firstmod.objects.minecraft
import sharkbound.forge.shared.extensions.applyTo
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.instance
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.pos
import sharkbound.forge.shared.extensions.removeAllGoals
import sharkbound.forge.shared.util.createAABB
import sharkbound.forge.shared.util.playerList
import sharkbound.forge.shared.util.rayTraceEntities
import java.lang.Exception
import kotlin.contracts.ExperimentalContracts

@Mod.EventBusSubscriber
object ServerEntityEvents {
    @SubscribeEvent
    @JvmStatic
    fun onConstructEntity(e: EntityJoinWorldEvent) {
        val ent = e.entity
        if (ent.world.isRemote || ent !is CreeperEntity) return
        ent.removeAllGoals()
        ent.goalSelector.addGoal(100, CreeperFollowPlayerGoal(ent))
    }

    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun serverTick(e: TickEvent.ServerTickEvent) {
        if (minecraft.integratedServer == null) return
        val player = playerList.players.firstOrNull() ?: return
        for (entity in rayTraceEntities<LivingEntity>(player, 5.0)) {
            Effects.GLOWING.instance(2).applyTo(entity).onKillCommand()
        }
    }
}
package sharkbound.forge.firstmod.events

import net.minecraft.entity.MobEntity
import net.minecraft.entity.ai.goal.FollowOwnerGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.monster.CreeperEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.potion.Effects
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.entities.goals.CreeperFollowPlayerGoal
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.instance
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.pos
import sharkbound.forge.shared.extensions.removeAllGoals
import sharkbound.forge.shared.util.createAABB
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

//    @ExperimentalContracts
//    @SubscribeEvent
//    @JvmStatic
//    fun playerMove(e: LivingEvent.LivingUpdateEvent) {
//    }
}
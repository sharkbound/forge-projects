package sharkbound.forge.firstmod.events

import net.minecraft.entity.ai.goal.FollowOwnerGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.monster.CreeperEntity
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.entities.goals.CreeperFollowPlayerGoal
import sharkbound.forge.shared.extensions.removeAllGoals

@Mod.EventBusSubscriber
object ServerEntityEvents {
    @SubscribeEvent
    @JvmStatic
    fun onConstructEntity(e: EntityJoinWorldEvent) {
        val ent = e.entity
        if (ent.world.isRemote || ent !is CreeperEntity) return
        ent.removeAllGoals()
        ent.goalSelector.addGoal(100, CreeperFollowPlayerGoal(ent))
//        ent.goalSelector.addGoal(0, MeleeAttackGoal(ent, 1.0, false))
    }
}
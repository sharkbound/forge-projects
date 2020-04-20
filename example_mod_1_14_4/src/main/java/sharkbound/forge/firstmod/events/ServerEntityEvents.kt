package sharkbound.forge.firstmod.events

import net.minecraft.entity.monster.CreeperEntity
import net.minecraftforge.event.entity.EntityEvent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.entities.goals.CreeperSpawnTNTGoal

@Mod.EventBusSubscriber
object ServerEntityEvents {
    @SubscribeEvent
    @JvmStatic
    fun onConstructEntity(e: EntityJoinWorldEvent) {
        val ent = e.entity
        if (ent.world.isRemote || ent !is CreeperEntity) return
        ent.goalSelector.addGoal(0, CreeperSpawnTNTGoal(ent))
    }
}
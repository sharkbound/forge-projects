package sharkbound.forge.firstmod.events.server

import net.minecraft.entity.monster.CreeperEntity
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.event.entity.ProjectileImpactEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.entities.goals.CreeperFollowPlayerGoal
import sharkbound.forge.shared.extensions.isClient
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.removeAllGoals
import kotlin.contracts.ExperimentalContracts

@Mod.EventBusSubscriber
object ServerEntityEvents {
    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun entityJoinWorld(e: EntityJoinWorldEvent) {
        val ent = e.entity
        if (ent.world.isClient() || ent !is CreeperEntity) return
        ent.removeAllGoals()
        ent.goalSelector.addGoal(CreeperFollowPlayerGoal.PRIORITY, CreeperFollowPlayerGoal(ent))
    }

    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun arrowImpactEvent(e: ProjectileImpactEvent) {
        if (e.entity !is ArrowEntity || !e.entity?.world.isServerWorld()) return
        if (e.entity.persistentData.getBoolean("removeonimpact")) {
            e.entity.remove()
        }
    }

    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun debugTick(e: TickEvent.ServerTickEvent) {
    }
}
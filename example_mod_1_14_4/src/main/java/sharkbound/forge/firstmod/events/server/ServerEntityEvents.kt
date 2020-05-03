package sharkbound.forge.firstmod.events.server

import net.minecraft.entity.MobEntity
import net.minecraft.entity.monster.CreeperEntity
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.particles.ParticleTypes
import net.minecraft.util.DamageSource
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.event.entity.ProjectileImpactEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.entities.goals.CreeperFollowPlayerGoal
import sharkbound.forge.firstmod.objects.minecraft
import sharkbound.forge.shared.extensions.isClient
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.particle
import sharkbound.forge.shared.extensions.pos
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.removeAllGoals
import sharkbound.forge.shared.extensions.vec3d.down
import sharkbound.forge.shared.util.findPlayer
import sharkbound.forge.shared.util.playerList
import sharkbound.forge.shared.util.rayTraceEntities
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
package sharkbound.forge.firstmod.entities.goals

import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.monster.CreeperEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.AxisAlignedBB
import sharkbound.forge.shared.extensions.dist
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.pos
import sharkbound.forge.shared.extensions.vec3d.minus
import sharkbound.forge.shared.extensions.vec3d.plus

class CreeperFollowPlayerGoal(val creeper: CreeperEntity) : Goal() {
    companion object {
        const val PRIORITY = 2
    }

    var ticks = 0

    override fun shouldExecute(): Boolean {
        return true
    }

    override fun tick() {
        creeper.attackTarget?.let {
            if (creeper dist it > 20) {
                creeper.attackTarget = null
            }
        }
        creeper.attackTarget = creeper.world.entitiesInAABB<PlayerEntity>(AxisAlignedBB(creeper.pos - 10, creeper.pos + 10))
                .firstOrNull { !it.isCreative && !it.isSpectator }
        creeper.navigator.tryMoveToEntityLiving(creeper.attackTarget ?: return, 1.0)
    }
}

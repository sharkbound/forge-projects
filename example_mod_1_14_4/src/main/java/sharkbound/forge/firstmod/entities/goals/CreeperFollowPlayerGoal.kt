package sharkbound.forge.firstmod.entities.goals

import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.monster.CreeperEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.AxisAlignedBB
import sharkbound.forge.shared.extensions.add
import sharkbound.forge.shared.extensions.dist
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.pos
import sharkbound.forge.shared.extensions.send
import sharkbound.forge.shared.extensions.subtract

class CreeperFollowPlayerGoal(val creeper: CreeperEntity) : Goal() {
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
        creeper.attackTarget = creeper.world.entitiesInAABB<PlayerEntity>(AxisAlignedBB(creeper.pos.subtract(10), creeper.pos.add(10))).firstOrNull()
        creeper.navigator.tryMoveToEntityLiving(creeper.attackTarget ?: return, 1.0)
    }
}

package sharkbound.forge.firstmod.entities.goals

import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.item.TNTEntity
import net.minecraft.entity.monster.CreeperEntity
import sharkbound.commonutils.util.randDouble
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.TickUnit

class CreeperSpawnTNTGoal(val creeper: CreeperEntity) : Goal() {
    var ticks = 0
    override fun shouldExecute(): Boolean {
        return ++ticks % 60 == 0
    }

    override fun tick() {
//        creeper.run {
//            world.addEntity(TNTEntity(world, posX, posY, posZ, null)
//                    .apply {
//                        fuse = 3.ticks(TickUnit.SECONDS)
//                        setVelocity(randDouble(-2, 2), randDouble(.3, 1.3), randDouble(-2, 2))
//                    })
//        }
    }

    override fun isPreemptible(): Boolean =
            false

    override fun shouldContinueExecuting(): Boolean {
        return false
    }
}
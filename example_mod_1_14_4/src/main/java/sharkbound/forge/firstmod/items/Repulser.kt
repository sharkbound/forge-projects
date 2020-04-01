package sharkbound.forge.firstmod.items

import net.minecraft.block.Blocks
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.RayTraceContext
import net.minecraft.world.World
import sharkbound.commonutils.extensions.max
import sharkbound.commonutils.extensions.min
import sharkbound.commonutils.util.randDouble
import sharkbound.commonutils.util.randInt
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.gui.ModContainers
import sharkbound.forge.firstmod.gui.container.RepulserContainer
import sharkbound.forge.firstmod.gui.screen.RepulserScreen
import sharkbound.forge.shared.extensions.addVel
import sharkbound.forge.shared.extensions.get
import sharkbound.forge.shared.extensions.isServerPlayer
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.mul
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.send
import sharkbound.forge.shared.extensions.setBlock
import sharkbound.forge.shared.extensions.toActionResult
import sharkbound.forge.shared.util.vec3D
import kotlin.contracts.ExperimentalContracts

class Repulser : Item(Properties().group(FirstModItemGroup)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, hand: Hand): ActionResult<ItemStack> {
        val range = 30
        val maxForce = range * .3
        if (world.isServerWorld()) {
            if (player.isServerPlayer() && player.isSneaking) {
                player.send(player.currentWindowId)
                player.openContainer(RepulserScreen(RepulserContainer(player.currentWindowId, player.inventory, player), player.inventory))
            } else {
                val ray = player.rayTraceBlocks(100.0, fluidMode = RayTraceContext.FluidMode.NONE)
                world.getEntitiesWithinAABB(Entity::class.java, AxisAlignedBB(ray.hitVec.subtract(vec3D(range, range, range)), ray.hitVec.add(vec3D(range, range, range)))).forEach {
                    val vel = it.positionVec.subtract(ray.hitVec).normalize().mul((maxForce - it.positionVec.distanceTo(ray.hitVec)) max 0.0)
                    it.addVel(vel)
                }
            }
        }
        return player[hand].toActionResult(ActionResultType.SUCCESS)
    }

    companion object {
        const val REGISTRY_NAME = "repulser"
    }
}
package sharkbound.forge.firstmod.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.ActionResult
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.RayTraceContext
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.ICapabilityProvider
import sharkbound.commonutils.extensions.min
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.gui.container.RepulserContainer
import sharkbound.forge.firstmod.gui.screen.RepulserScreen
import sharkbound.forge.firstmod.networking.Network
import sharkbound.forge.firstmod.networking.packets.RepulserRadiusChangePacket
import sharkbound.forge.shared.extensions.addAll
import sharkbound.forge.shared.extensions.vec3d.dist
import sharkbound.forge.shared.extensions.get
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.isServerPlayer
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.vec3d.mul
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.setVel
import sharkbound.forge.shared.extensions.toActionResult
import sharkbound.forge.shared.util.vec3D
import java.util.*
import kotlin.contracts.ExperimentalContracts
import kotlin.math.absoluteValue

class Repulser : Item(Properties().group(FirstModItemGroup)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, hand: Hand): ActionResult<ItemStack> {
        val range = radiusOf(player.item.stack)
        val maxForce = range * .3
        if (world.isServerWorld() && player.isServerPlayer()) {
            if (player.isSneaking) {
                showGUI(player)
            } else {
                pushMobs(player, world, range, maxForce)
            }
        }
        return player[hand].toActionResult(ActionResultType.SUCCESS)
    }

    override fun initCapabilities(stack: ItemStack?, nbt: CompoundNBT?): ICapabilityProvider? {
        if (stack?.hasTag() == false) {
            setRadius(stack, 20)
        }
        return null
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        tooltip.addAll("&aCurrent range: ${radiusOf(stack)}")
    }

    private fun pushMobs(player: PlayerEntity, world: World, range: Int, maxForce: Double) {
        val ray = player.rayTraceBlocks(200.0, fluidMode = RayTraceContext.FluidMode.NONE)
        world.getEntitiesWithinAABB(Entity::class.java, AxisAlignedBB(ray.hitVec.subtract(vec3D(range, range, range)), ray.hitVec.add(vec3D(range, range, range)))).forEach {
            val normalized = it.positionVec.subtract(ray.hitVec).normalize()
            val dist = it.positionVec dist ray.hitVec
            val vel = normalized.mul((maxForce * (1 - (dist / range))).absoluteValue min 5.0)
            it.setVel(vel)
        }
    }

    private fun showGUI(player: ServerPlayerEntity) {
        player.openContainer(RepulserScreen(RepulserContainer(player.currentWindowId, player.inventory, player), player.inventory))
    }

    companion object {
        const val REGISTRY_NAME = "repulser"
        const val RADIUS_KEY = "radius"

        fun radiusOf(stack: ItemStack?): Int =
                stack?.orCreateTag?.getInt(RADIUS_KEY) ?: 0

        fun setRadius(stack: ItemStack?, radius: Int) {
            stack?.orCreateTag?.putInt(RADIUS_KEY, radius)
        }

        fun sendRadiusPacket(playerId: UUID, newRadius: Int) {
            Network.channel.sendToServer(RepulserRadiusChangePacket(playerId, newRadius))
        }
    }
}
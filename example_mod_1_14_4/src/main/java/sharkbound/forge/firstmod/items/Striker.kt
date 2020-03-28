package sharkbound.forge.firstmod.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.item.FallingBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.util.*
import net.minecraft.util.math.RayTraceContext
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.*
import kotlin.contracts.ExperimentalContracts

class Striker : Item(Properties().maxStackSize(1).group(FirstModItemGroup)) {
    init {
        setRegistryName("striker")
    }

    override fun getDisplayName(stack: ItemStack): ITextComponent {
        return toText("&6Striker")
    }

    @ExperimentalContracts
    fun callStrike(world: World, player: PlayerEntity, radius: Double) {
        if (world.isServerWorld() && player.isServerPlayer()) {
            player.rayTraceBlocks(100.0, fluidMode = RayTraceContext.FluidMode.ANY).run {
                blocksInRadius(pos, radius.toInt())
                        .filter { it.withinDistance(hitVec, radius - .5) }
                        .forEach {
                            if (!it.isAir(world)) {
                                val state = it.state(world)
                                it.setToAir(world)
                                val fallingBlock = FallingBlockEntity(world, hitVec.x, hitVec.y, hitVec.z, state)
                                world.addEntity(fallingBlock)
                                player.positionVec.add(vector(y = 5)).subtract(it.toVec3d()).normalize().mul(1.5).run {
                                    fallingBlock.setPos(it.toVec3d())
                                    fallingBlock.addVelocity(x, y, z)
                                    fallingBlock.fallTime = 1
                                }
                            }
                        }
                world.doLightningStrike(hitVec)
            }
        }
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        callStrike(world, player, 5.0)
        return ActionResult(ActionResultType.SUCCESS, player.heldItemInfo.stack)
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        tooltip.addAll("&eCalls down a lightning strike upon your enemies!")
    }
}
package sharkbound.forge.firstmod.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.item.FallingBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceContext
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.util.delayTask
import sharkbound.forge.firstmod.util.runningTickHandlers
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
            player.rayTraceBlocks(100.0).run {
                delayTask(ticks(5, TickUnit.SECONDS)) {
                    world.doLightningStrike(hitVec)
                }
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
package sharkbound.forge.firstmod.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.util.*
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import sharkbound.commonutils.util.*
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.util.delayTask
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

    val r get() = randDouble(-4, 4)

    @ExperimentalContracts
    fun callStrike(world: World, player: PlayerEntity, radius: Double) {
        if (world.isServerWorld() && player.isServerPlayer()) {
            player.rayTraceBlocks(100.0).run {
                delayTask(toTicks(5, TickUnit.SECONDS)) {
                    repeat(randRange(100)) {
                        world.doLightningStrike(hitVec.add(r, 0.0, r))
                    }
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
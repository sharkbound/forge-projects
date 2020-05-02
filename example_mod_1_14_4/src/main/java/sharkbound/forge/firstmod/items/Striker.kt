package sharkbound.forge.firstmod.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import sharkbound.commonutils.util.randDouble
import sharkbound.commonutils.util.randRange
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.events.scheduler.delayTask
import sharkbound.forge.shared.extensions.addAll
import sharkbound.forge.shared.extensions.doLightningStrike
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.isServerPlayer
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.asText
import kotlin.contracts.ExperimentalContracts

class Striker : Item(Properties().maxStackSize(1).group(FirstModItemGroup)) {
    init {
        setRegistryName("striker")
    }

    override fun getDisplayName(stack: ItemStack): ITextComponent {
        return asText("&6Striker")
    }

    val r get() = randDouble(-4, 4)

    @ExperimentalContracts
    fun callStrike(world: World, player: PlayerEntity) {
        if (world.isServerWorld() && player.isServerPlayer()) {
            player.rayTraceBlocks(100.0).run {
                delayTask(5.ticks(TickUnit.SECONDS)) {
                    repeat(randRange(100)) {
                        world.doLightningStrike(hitVec.add(r, 0.0, r))
                    }
                }
            }
        }
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        callStrike(world, player)
        return ActionResult(ActionResultType.SUCCESS, player.item.stack)
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        tooltip.addAll("&eCalls down a lightning strike upon your enemies!")
    }
}
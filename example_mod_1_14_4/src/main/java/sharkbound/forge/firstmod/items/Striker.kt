package sharkbound.forge.firstmod.items

import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.block.Blocks
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.effect.LightningBoltEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.util.*
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import sharkbound.commonutils.util.*
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.data.minecraft
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.blocksInRadius
import sharkbound.forge.shared.util.toText
import kotlin.contracts.ExperimentalContracts

class Striker : Item(Properties().maxStackSize(1).group(FirstModItemGroup)) {
    init {
        setRegistryName("striker")
    }

    override fun getDisplayName(stack: ItemStack): ITextComponent {
        return toText("&6Striker")
    }

    @ExperimentalContracts
    fun callStrike(world: World, player: PlayerEntity) {

        if (world.isServerWorld() && player.isServerPlayer()) {
            player.rayTraceBlocks(100.0).run {
                blocksInRadius(pos, 5).forEach {
                    if (randDouble(0.0, 1.0) < .05) {
//                        world.setBlockState(it, Blocks.AIR.defaultState)
                        it.setToAir(world)
                    }
                }
                world.doLightningStrike(hitVec)
            }
        }
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        callStrike(world, player)
        return ActionResult(ActionResultType.SUCCESS, player.heldItemInfo.stack)
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        tooltip.addAll("&eCalls down a lightning strike upon your enemies!")
    }
}
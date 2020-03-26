package sharkbound.forge.firstmod.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.EntityType
import net.minecraft.entity.effect.LightningBoltEntity
import net.minecraft.entity.passive.PigEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.util.*
import net.minecraft.util.math.*
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.newVec3D
import sharkbound.forge.shared.util.text
import kotlin.contracts.ExperimentalContracts

class Striker : Item(Properties().maxStackSize(1).group(FirstModItemGroup)) {
    init {
        setRegistryName("striker")
    }

    override fun getDisplayName(stack: ItemStack): ITextComponent {
        return text("&6Striker")
    }

    @ExperimentalContracts
    fun callStrike(world: World, player: PlayerEntity) {
        if (world.isServerWorld() && player.isServerPlayer()) {
            player.rayTraceBlocks(100.0).run {
                world.addLightningBolt(LightningBoltEntity(world, pos.xd, pos.yd, pos.zd, false))
            }
        }
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        callStrike(world, player)
        return ActionResult(ActionResultType.SUCCESS, player.heldItemInfo.stack)
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        tooltip.add(text("&eCalls down a lightning strike upon your enemies!"))
    }
}
package sharkbound.forge.firstmod.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.*
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.ICapabilityProvider
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.interfaces.HasRegistryName
import sharkbound.forge.firstmod.potions.ModEffects
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.text
import kotlin.contracts.ExperimentalContracts


class MehWand : Item(Properties().maxStackSize(64).group(FirstModItemGroup)) {
    override fun getDisplayName(stack: ItemStack): ITextComponent =
            text("&aMeh Wand (&e${getMode(stack)}&a)")

    init {
        setRegistryName(REGISTRY_NAME)
    }


    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        tooltip.addAll(listOf(
                text("&3Mode: ${getMode(stack)}")
        ))
    }

    override fun hitEntity(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean =
            target.addEffect(stack)

    override fun initCapabilities(stack: ItemStack?, nbt: CompoundNBT?): ICapabilityProvider? {
        stack?.let {
            setMode(it, Mode.DUPLICATE)
        }
        return null
    }

    @ExperimentalContracts
    override fun onItemRightClick(worldIn: World, player: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        if (worldIn.isServerWorld() && player.isSneaking) {
            player.heldItemInfo.stack.run {
                advanceMode(this)
                player.send("&aset mode to &e${getMode(stack)}")
            }
        }
        return ActionResult(ActionResultType.SUCCESS, player.heldItemInfo.stack)
    }

    override fun itemInteractionForEntity(stack: ItemStack, playerIn: PlayerEntity, target: LivingEntity, hand: Hand): Boolean =
            target.addEffect(stack)


    companion object : HasRegistryName {
        const val MODE_KEY = "mode"

        override val REGISTRY_NAME: String
            get() = "mehwand"

        fun getMode(stack: ItemStack): Mode =
                stack.orCreateTag.getByte(MODE_KEY).let { id -> Mode.values().first { it.numberId == id } }

        fun setMode(stack: ItemStack, newMode: Mode) =
                stack.orCreateTag.putByte(MODE_KEY, newMode.numberId)

        fun advanceMode(stack: ItemStack) {
            setMode(stack, getMode(stack).next())
        }
    }

    enum class Mode(val numberId: Byte) {
        DUPLICATE(0),
        DELETE(1),
        DESTROY_CHAIN(2);

        fun next() =
                when (this) {
                    DUPLICATE -> DELETE
                    DELETE -> DESTROY_CHAIN
                    DESTROY_CHAIN -> DUPLICATE
                }

        override fun toString(): String =
                when (this) {
                    DUPLICATE -> "Duplicate"
                    DELETE -> "Delete"
                    DESTROY_CHAIN -> "Destroy Chain"
                }
    }
}


private fun LivingEntity.addEffect(stack: ItemStack): Boolean {
    addPotionEffect(ModEffects.CHAOS.instance(stack.count * 20, particles = false))
    return true
}
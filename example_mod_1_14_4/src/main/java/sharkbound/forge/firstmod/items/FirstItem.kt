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
import sharkbound.forge.firstmod.potions.ChaosEffect
import sharkbound.forge.firstmod.potions.ModEffects
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.text
import kotlin.contracts.ExperimentalContracts


class FirstItem : Item(Properties().maxStackSize(64).group(FirstModItemGroup)) {
    enum class Mode(val numberId: Byte) {
        DUPLICATE(0), DELETE(1);

        fun next() =
                when (this) {
                    DUPLICATE -> DELETE
                    DELETE -> DUPLICATE
                }

        val displayName = name.toLowerCase().capitalize()
    }

    var ItemStack.mode
        get() = orCreateTag.getByte(MODE_KEY).let { id -> Mode.values().first { it.numberId == id } }
        set(value) {
            orCreateTag.putByte(MODE_KEY, value.numberId)
        }

    override fun getDisplayName(stack: ItemStack): ITextComponent =
            text("&aFirst Item (&e${stack.mode.displayName}&a)")

    init {
        setRegistryName(REGISTRY_NAME)
    }


    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        tooltip.addAll(listOf(
                text("&3Mode: ${stack.mode.displayName}")
        ))
    }

    override fun hitEntity(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean =
            target.addEffect(stack)

    override fun initCapabilities(stack: ItemStack?, nbt: CompoundNBT?): ICapabilityProvider? {
        if (stack == null) return null
        stack.mode = Mode.DUPLICATE
        return null
    }

    @ExperimentalContracts
    override fun onItemRightClick(worldIn: World, player: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        if (worldIn.isServerWorld() && player.isSneaking) {
            player.heldItemInfo.stack.run {
                mode = mode.next()
                player.send("&aset mode to &e${stack.mode.displayName}")
            }
        }
        return ActionResult(ActionResultType.SUCCESS, player.heldItemInfo.stack)
    }

    override fun itemInteractionForEntity(stack: ItemStack, playerIn: PlayerEntity, target: LivingEntity, hand: Hand): Boolean =
            target.addEffect(stack)


    companion object : HasRegistryName {
        const val MODE_KEY = "mode"

        override val REGISTRY_NAME: String
            get() = "firstitem"
    }
}


private fun LivingEntity.addEffect(stack: ItemStack): Boolean {
    addPotionEffect(ModEffects.CHAOS.instance(stack.count * 20, particles = false))
    return true
}
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
import sharkbound.commonutils.extensions.choice
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.data.ModBlocks
import sharkbound.forge.firstmod.potions.ModEffects
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.text
import kotlin.contracts.ExperimentalContracts


class MehWand : Item(Properties().maxStackSize(64).group(FirstModItemGroup)) {
    enum class Mode(val numberId: Byte) {
        DUPLICATE(0),
        DESTROY(1),
        DESTROY_CHAIN(2),
        REPLACE(3),
        REPLACE_OFFSET(4);

        fun next() =
                when (this) {
                    DUPLICATE -> DESTROY
                    DESTROY -> DESTROY_CHAIN
                    DESTROY_CHAIN -> REPLACE
                    REPLACE -> REPLACE_OFFSET
                    REPLACE_OFFSET -> DUPLICATE
                }

        override fun toString(): String =
                when (this) {
                    DUPLICATE -> "Duplicate"
                    DESTROY -> "Destroy"
                    DESTROY_CHAIN -> "Destroy Chain"
                    REPLACE -> "Replace"
                    REPLACE_OFFSET -> "Replace Offset"
                }
    }

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
            if (MODE_NBT_KEY !in it.orCreateTag)
                setMode(it, Mode.DUPLICATE)
        }
        return null
    }

    override fun onItemUse(c: ItemUseContext): ActionResultType {
        c.run {
            val mode = getMode(item)
            val isMeh = pos.isBlock(world, ModBlocks.MEH_BLOCK)
            when {
                mode == Mode.DESTROY -> pos.destroyBlock(world)
                mode == Mode.DUPLICATE && isMeh -> {
                    pos.offset(allDirections.choice()).setBlock(world, ModBlocks.MEH_BLOCK)
                }
                mode == Mode.DESTROY_CHAIN && isMeh -> ModBlocks.MEH_BLOCK.destroyChain(pos, world)
                mode == Mode.REPLACE -> pos.setBlock(world, ModBlocks.MEH_BLOCK)
                mode == Mode.REPLACE_OFFSET -> pos.offset(face).setBlock(world, ModBlocks.MEH_BLOCK)
            }
        }
        return ActionResultType.SUCCESS
    }

    override fun onUsingTick(stack: ItemStack?, player: LivingEntity?, count: Int) {
        println("TICK")
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

    companion object {
        const val MODE_NBT_KEY = "mode"
        const val REGISTRY_NAME = "mehwand"

        val allDirections = enumValues<Direction>()

        fun getMode(stack: ItemStack): Mode =
                stack.orCreateTag.getByte(MODE_NBT_KEY).let { id -> Mode.values().first { it.numberId == id } }

        fun setMode(stack: ItemStack, newMode: Mode) =
                stack.orCreateTag.putByte(MODE_NBT_KEY, newMode.numberId)

        fun advanceMode(stack: ItemStack) {
            setMode(stack, getMode(stack).next())
        }

    }
}


private fun LivingEntity.addEffect(stack: ItemStack): Boolean {
    addPotionEffect(ModEffects.CHAOS.instance(stack.count * 20, particles = false))
    return true
}
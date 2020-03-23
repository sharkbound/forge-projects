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
import sharkbound.commonutils.extensions.len
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.data.ModBlocks
import sharkbound.forge.firstmod.networking.Network
import sharkbound.forge.firstmod.networking.packets.MehWandModeSwitchPacket
import sharkbound.forge.firstmod.potions.ModEffects
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.text
import java.util.*
import kotlin.contracts.ExperimentalContracts


class MehWand : Item(Properties().maxStackSize(64).group(FirstModItemGroup)) {
    enum class Mode {
        DUPLICATE,
        DESTROY,
        DESTROY_CHAIN,
        REPLACE,
        REPLACE_OFFSET;

        companion object {
            val ALL = values()
        }

        fun prev() =
                ALL[(ordinal - 1).let { if (it < 0) ALL.lastIndex else it }]

        fun next() =
                ALL[(ordinal + 1) % ALL.len]

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
            text("&aMeh Wand (&e${modeOf(stack)}&a)")


    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        tooltip.addAll(listOf(
                text("&3Mode: ${modeOf(stack)}")
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

    @ExperimentalContracts
    override fun onItemUse(c: ItemUseContext): ActionResultType {
        if (c.world.isServerWorld()) {
            c.run {
                val mode = modeOf(item)
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
        }
        return ActionResultType.SUCCESS
    }

    @ExperimentalContracts
    override fun onItemRightClick(worldIn: World, player: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        if (worldIn.isServerWorld() && player.isSneaking) {
            player.heldItemInfo.stack.run {
                advanceMode(this)
                sendModeUpdateMessage(player, this)
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

        fun modeOf(stack: ItemStack): Mode =
                stack.orCreateTag.getInt(MODE_NBT_KEY).let { id -> Mode.values().first { it.ordinal == id } }

        fun setMode(stack: ItemStack, newMode: Mode) =
                stack.orCreateTag.putInt(MODE_NBT_KEY, newMode.ordinal)

        fun advanceMode(stack: ItemStack) {
            setMode(stack, modeOf(stack).next())
        }

        fun sendModeUpdateMessage(player: PlayerEntity, stack: ItemStack) {
            player.send("&aset mode to &e${modeOf(stack)}")
        }

        fun sendModeUpdatePacket(playerId: UUID, newMode: Mode) {
            Network.channel.sendToServer(MehWandModeSwitchPacket(playerId, newMode))
        }
    }
}


private fun LivingEntity.addEffect(stack: ItemStack): Boolean {
    addPotionEffect(ModEffects.CHAOS.instance(stack.count * 20, particles = false))
    return true
}
package sharkbound.forge.firstmod.items

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.world.World
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.interfaces.HasRegistryName
import sharkbound.forge.firstmod.potions.ChaosEffect
import sharkbound.forge.shared.extensions.*
import kotlin.contracts.ExperimentalContracts


class FirstItem : Item(Properties().maxStackSize(64).group(FirstModItemGroup)) {
    enum class Mode {
        DUPLICATE
    }

    var currentMode = Mode.DUPLICATE

    init {
        setRegistryName(REGISTRY_NAME)
    }

    private fun LivingEntity.addEffect(stack: ItemStack): Boolean {
        addPotionEffect(ChaosEffect().instance(stack.count * 20, particles = false))
        return true
    }

    override fun hitEntity(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean =
            target.addEffect(stack)

    override fun itemInteractionForEntity(stack: ItemStack, playerIn: PlayerEntity, target: LivingEntity, hand: Hand): Boolean =
            target.addEffect(stack)

    @ExperimentalContracts
    override fun onItemRightClick(worldIn: World, player: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        player.send(player.heldItemInfo.item.javaClass)
        return ActionResult(ActionResultType.SUCCESS, player.heldItemInfo.stack)
    }

    companion object : HasRegistryName {
        override val REGISTRY_NAME: String
            get() = "firstitem"
    }
}
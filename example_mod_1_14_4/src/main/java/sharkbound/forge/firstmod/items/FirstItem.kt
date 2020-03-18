package sharkbound.forge.firstmod.items

import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.interfaces.HasRegistryName
import sharkbound.forge.shared.extensions.setInAir

class FirstItem : Item(Properties().maxStackSize(128).group(FirstModItemGroup)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun hitEntity(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        target.setInAir()
        target.addVelocity(0.0, stack.count.toDouble() / 4, 0.0)
        return true
    }

    companion object : HasRegistryName {
        override val REGISTRY_NAME: String
            get() = "firstitem"
    }
}
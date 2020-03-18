package sharkbound.forge.firstmod.items

import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.EffectInstance
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.interfaces.HasRegistryName
import sharkbound.forge.firstmod.potions.ChaosEffect

class FirstItem : Item(Properties().maxStackSize(1).group(FirstModItemGroup)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun hitEntity(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        target.addPotionEffect(EffectInstance(ChaosEffect(), 200))
        return true
    }

    companion object : HasRegistryName {
        override val REGISTRY_NAME: String
            get() = "firstitem"
    }
}
package sharkbound.forge.firstmod.enchantments

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentType
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.inventory.EquipmentSlotType
import sharkbound.commonutils.util.randDouble
import sharkbound.forge.shared.extensions.isLivingEntity
import sharkbound.forge.shared.extensions.setVel
import sharkbound.forge.shared.util.vec3D
import kotlin.contracts.ExperimentalContracts

class DisplacementEnchantment : Enchantment(Rarity.COMMON, EnchantmentType.ALL, arrayOf(EquipmentSlotType.MAINHAND)) {
    companion object {
        const val REGISTRY_NAME = "displacement"
    }

    @ExperimentalContracts
    override fun onEntityDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (target.isLivingEntity()) {
            val maxVel = 2
            target.setVel(vec3D(randDouble(-maxVel, maxVel), randDouble(.5, 2.0), randDouble(-maxVel, maxVel)))
        }
    }
}
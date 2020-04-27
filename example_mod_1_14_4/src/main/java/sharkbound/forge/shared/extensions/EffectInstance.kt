package sharkbound.forge.shared.extensions

import net.minecraft.entity.LivingEntity
import net.minecraft.potion.EffectInstance

fun <T : LivingEntity> EffectInstance.applyTo(entity: T): T = entity.apply {
    if (isAlive) {
        addPotionEffect(this@applyTo)
    }
}
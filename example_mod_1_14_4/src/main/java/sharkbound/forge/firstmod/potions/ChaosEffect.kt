package sharkbound.forge.firstmod.potions

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.attributes.AbstractAttributeMap
import net.minecraft.particles.ParticleTypes
import net.minecraft.potion.Effect
import net.minecraft.potion.EffectType
import net.minecraft.util.text.StringTextComponent
import sharkbound.commonutils.util.randDouble
import sharkbound.forge.shared.extensions.color
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.particle
import sharkbound.forge.shared.extensions.teleportRelative
import java.text.DecimalFormat
import kotlin.contracts.ExperimentalContracts

class ChaosEffect : Effect(EffectType.NEUTRAL, 0xff0000) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun getName() =
            "Chaos"

    companion object {
        const val REGISTRY_NAME = "chaoseffect"
    }

    override fun isReady(duration: Int, amplifier: Int): Boolean {
        return true
    }

    private val offset get() = randDouble(-.1, .101)
    private val format = DecimalFormat("#0.0")

    @ExperimentalContracts
    override fun performEffect(e: LivingEntity, amplifier: Int) {
        e.setNoGravity(true)
        e.teleportRelative(offset, offset, offset)

        val left = format.format((e.activePotionEffects.firstOrNull { it.potion is ChaosEffect }?.duration ?: -1) / 20.0)
        e.customName = StringTextComponent("&dChaos: &e$left".color())
        e.isCustomNameVisible = true
        e.world.let {
            if (it.isServerWorld()) {
                it.particle(ParticleTypes.LARGE_SMOKE, e.posX, e.posY, e.posZ)
            }
        }
    }

    override fun removeAttributesModifiersFromEntity(e: LivingEntity, attributeMapIn: AbstractAttributeMap, amplifier: Int) {
        e.setNoGravity(false)
    }
}
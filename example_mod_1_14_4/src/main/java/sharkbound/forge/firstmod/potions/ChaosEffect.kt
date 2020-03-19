package sharkbound.forge.firstmod.potions

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.attributes.AbstractAttributeMap
import net.minecraft.particles.ParticleTypes
import net.minecraft.potion.*
import net.minecraft.util.text.StringTextComponent
import sharkbound.commonutils.util.*
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.interfaces.HasRegistryName
import sharkbound.forge.shared.extensions.*
import java.text.DecimalFormat
import java.text.NumberFormat
import javax.swing.text.NumberFormatter
import kotlin.contracts.ExperimentalContracts

class ChaosEffect : Effect(EffectType.NEUTRAL, 0xff0000) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun getName() =
            "Chaos"

    companion object : HasRegistryName {
        override val REGISTRY_NAME: String
            get() = "chaoseffect"

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
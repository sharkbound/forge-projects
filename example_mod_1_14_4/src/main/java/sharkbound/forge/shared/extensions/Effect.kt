package sharkbound.forge.shared.extensions

import net.minecraft.potion.Effect
import net.minecraft.potion.EffectInstance

fun <T : Effect> T.instance(duration: Int, amplifier: Int = 1, ambient: Boolean = false, particles: Boolean = true): EffectInstance =
        EffectInstance(this, duration, amplifier, ambient, particles)
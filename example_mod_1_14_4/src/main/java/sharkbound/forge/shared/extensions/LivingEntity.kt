package sharkbound.forge.shared.extensions

import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.Vec3d

val LivingEntity.eyePos: Vec3d
    get() = getEyePosition(1f)
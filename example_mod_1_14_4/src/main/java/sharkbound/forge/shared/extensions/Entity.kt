package sharkbound.forge.shared.extensions

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.text.StringTextComponent
import sharkbound.forge.shared.extensions.vec3d.dist
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun Entity.setInAir() {
    isAirBorne = true
    onGround = false
}

fun Entity.teleportRelative(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    teleportKeepLoaded(posX + x, posY + y, posZ + z)
}

fun Entity.teleportRelative(x: Int = 0, y: Int = 0, z: Int = 0) {
    teleportKeepLoaded(posX + x, posY + y, posZ + z)
}

fun Entity.send(obj: Any?, altChar: Char = '&') {
    sendMessage(StringTextComponent(obj.toString().color(altChar)))
}

fun <T : Entity> T.setPos(pos: Vec3d): T = apply {
    setPosition(pos.x, pos.y, pos.z)
}

fun <T : Entity> T.setVel(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0): T = apply {
    setVelocity(x, y, z)
}

fun <T : Entity> T.setVel(vec3d: Vec3d): T = apply {
    setVelocity(vec3d.x, vec3d.y, vec3d.z)
}

fun <T : Entity> T.addVel(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0): T = apply {
    addVelocity(x, y, z)
}

fun <T : Entity> T.addVel(vec3d: Vec3d): T = apply {
    addVelocity(vec3d.x, vec3d.y, vec3d.z)
}

infix fun Entity.dist(other: Entity): Double =
        positionVec dist other.positionVec

infix fun Entity.dist(other: Vec3d): Double =
        positionVec dist other

val Entity.pos: Vec3d
    get() = positionVec

val Entity.block: BlockPos
    get() = position

@ExperimentalContracts
fun Entity.isLivingEntity(): Boolean {
    contract {
        returns(true) implies (this@isLivingEntity is LivingEntity)
    }
    return this is LivingEntity
}

val Entity.eyePos: Vec3d
    get() = getEyePosition(1f)

val Entity?.nameOrCustom: String
    get() = when {
        this == null -> ""
        else -> customName?.unformattedComponentText ?: name.unformattedComponentText
    }

val Entity?.nameOrCustomLower: String
    get() = nameOrCustom.toLowerCase()
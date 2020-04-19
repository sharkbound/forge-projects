package sharkbound.forge.shared.extensions

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.text.StringTextComponent
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

fun Entity.setPos(pos: Vec3d) =
        apply {
            setPosition(pos.x, pos.y, pos.z)
        }

fun Entity.setVel(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    setVelocity(x, y, z)
}

fun Entity.setVel(vec3d: Vec3d) {
    setVelocity(vec3d.x, vec3d.y, vec3d.z)
}

fun Entity.addVel(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    addVelocity(x, y, z)
}

fun Entity.addVel(vec3d: Vec3d) {
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
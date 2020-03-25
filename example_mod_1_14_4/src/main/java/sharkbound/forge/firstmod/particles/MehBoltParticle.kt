package sharkbound.forge.firstmod.particles

import net.minecraft.client.particle.*
import net.minecraft.client.renderer.ActiveRenderInfo
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import sharkbound.forge.firstmod.particles.data.MehBoltParticleData
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.TickUnit

class MehBoltParticle(val world: World, x: Double, y: Double, z: Double, val target: Vec3d) : Particle(world, x, y, z, 0.0, 0.0, 0.0) {
    init {
        particleRed = 1f
        particleBlue = 0f
        particleGreen = 1f
        posX = x
        posY = y
        posZ = z
        prevPosX = posX
        prevPosY = posY
        prevPosZ = posZ
    }

    override fun tick() {
        prevPosX = posX
        prevPosY = posY
        prevPosZ = posZ
        if (++age > getMaxAge()) {
            setExpired()
        }
        move(0.0, .1, 0.0)
    }

    override fun getMaxAge(): Int {
        return 1.ticks(TickUnit.SECONDS)
    }

    override fun getRenderType(): IParticleRenderType {
        return IParticleRenderType.CUSTOM
    }

    override fun renderParticle(buffer: BufferBuilder, entityIn: ActiveRenderInfo, partialTicks: Float, rotationX: Float, rotationZ: Float, rotationYZ: Float, rotationXY: Float, rotationXZ: Float) {
//        GlStateManager.depthMask(false)
//        GlStateManager.enableBlend()
//        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE)
//        GlStateManager.disableCull()
//        GlStateManager.pushMatrix()
//        GlStateManager.translated(posX - entityIn.projectedView.x, posY - entityIn.projectedView.y, posZ - entityIn.projectedView.z)
//        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f)

//        GlStateManager.color4f(1f, 1f, 1f, 1f)
//        GlStateManager.enableCull()
//        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
//        GlStateManager.disableBlend()
//        GlStateManager.depthMask(true)
//        GlStateManager.popMatrix()
    }

    companion object {
        lateinit var SPRITES: IAnimatedSprite
    }

    class Factory(val sprites: IAnimatedSprite) : IParticleFactory<MehBoltParticleData> {
        init {
            SPRITES = sprites
        }

        override fun makeParticle(typeIn: MehBoltParticleData, worldIn: World, x: Double, y: Double, z: Double, xSpeed: Double, ySpeed: Double, zSpeed: Double): Particle? {
            return MehBoltParticle(worldIn, x, y, z, typeIn.target)
        }
    }
}
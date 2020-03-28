package sharkbound.forge.firstmod.particles

import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.client.particle.*
import net.minecraft.client.renderer.*
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.lwjgl.opengl.GL11
import sharkbound.forge.firstmod.data.minecraft
import sharkbound.forge.firstmod.particles.data.MehBoltParticleData
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.TickUnit

class MehBoltParticle(val world: World, x: Double, y: Double, z: Double, val target: Vec3d) : Particle(world, x, y, z, 0.0, 0.0, 0.0) {
    init {
        particleRed = 1f
        particleBlue = 0f
        particleGreen = 1f
        particleAlpha = 1f
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

    fun BufferBuilder.withParticleColors() =
            color(particleRed, particleGreen, particleBlue, particleAlpha)

    override fun renderParticle(buffer: BufferBuilder, e: ActiveRenderInfo, partialTicks: Float, rotationX: Float, rotationZ: Float, rotationYZ: Float, rotationXY: Float, rotationXZ: Float) {
        minecraft.textureManager.bindTexture(ResourceLocation("textures/particle/meh_bolt.png"))
        val sprite = SPRITES[rand]
        val minV = sprite.minV.toDouble()
        val maxV = sprite.maxV.toDouble()
        val minU = sprite.minU.toDouble()
        val maxU = sprite.maxU.toDouble()
        val offset = 1.0
        val baseRotation = (e.renderViewEntity.rotationYaw - e.renderViewEntity.prevRotationYaw) * partialTicks + e.renderViewEntity.prevRotationYaw
//
        GlStateManager.disableCull()
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL)
        buffer.pos(0.0, 100.0, 0.0).tex(maxU, maxV).withParticleColors().normal(0f, 1f, 0f).endVertex()
        buffer.pos(100.0, 50.0, 0.0).tex(maxU, minV).withParticleColors().normal(1f, 1f, 1f).endVertex()
        buffer.pos(100.0, 0.0, 0.0).tex(minU, minV).withParticleColors().normal(1f, 0f, 0f).endVertex()
        buffer.pos(0.0, 0.0, 0.0).tex(minU, maxV).withParticleColors().normal(0f, 0f, 0f).endVertex()
        buffer.endVertex()
        Tessellator.getInstance().draw()
//        GlStateManager.enableCull()
//

//        GlStateManager.depthMask(false)
//        GlStateManager.enableBlend()
//        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE)
//        GlStateManager.disableCull()
//        GlStateManager.pushMatrix()
//        GlStateManager.translated(posX - entityIn.projectedView.x, posY - entityIn.projectedView.y, posZ - entityIn.projectedView.z)
//        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f)
//
//        val tess = Tessellator.getInstance()
//        tess.draw()
//
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

    class Factory(sprites: IAnimatedSprite) : IParticleFactory<MehBoltParticleData> {
        init {
            SPRITES = sprites
        }

        override fun makeParticle(typeIn: MehBoltParticleData, worldIn: World, x: Double, y: Double, z: Double, xSpeed: Double, ySpeed: Double, zSpeed: Double): Particle? {
            return MehBoltParticle(worldIn, x, y, z, typeIn.target)
        }
    }
}
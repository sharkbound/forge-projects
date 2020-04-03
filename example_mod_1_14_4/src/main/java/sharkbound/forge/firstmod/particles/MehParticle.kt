package sharkbound.forge.firstmod.particles

import net.minecraft.client.particle.IAnimatedSprite
import net.minecraft.client.particle.IParticleFactory
import net.minecraft.client.particle.IParticleRenderType
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.SpriteTexturedParticle
import net.minecraft.particles.BasicParticleType
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import sharkbound.commonutils.util.randDouble
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.TickUnit

@OnlyIn(Dist.CLIENT)
class MehParticle(val world: World, x: Double, y: Double, z: Double, val sprites: IAnimatedSprite) : SpriteTexturedParticle(world, x, y, z) {
    init {
        selectSpriteRandomly(sprites)
        val horizonalRange = .2
        motionX = randDouble(-horizonalRange, horizonalRange)
        motionY = .6
        motionZ = randDouble(-horizonalRange, horizonalRange)
        age = 0
        particleScale = randDouble(.01, 1.0).toFloat()
    }

    override fun tick() {
        prevPosX = posX
        prevPosY = posY
        prevPosZ = posZ

        if (++age > getMaxAge() || onGround) {
            setExpired()
            return
        }

        motionY -= .02
        move(motionX, motionY, motionZ)
    }

    override fun getMaxAge(): Int {
        return 5.ticks(TickUnit.SECONDS)
    }

    override fun getRenderType(): IParticleRenderType =
            IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT

    class Factory(val spriteSheet: IAnimatedSprite) : IParticleFactory<BasicParticleType> {
        override fun makeParticle(typeIn: BasicParticleType, world: World, x: Double, y: Double, z: Double, xSpeed: Double, ySpeed: Double, zSpeed: Double): Particle? {
            return MehParticle(world, x, y, z, spriteSheet)
        }
    }
}
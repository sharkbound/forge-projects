package sharkbound.forge.firstmod.particles

import net.minecraft.client.particle.*
import net.minecraft.particles.BasicParticleType
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import sharkbound.commonutils.util.randDouble
import sharkbound.commonutils.util.randInt

@OnlyIn(Dist.CLIENT)
class MehParticle(val world: World, x: Double, y: Double, z: Double, val sprites: IAnimatedSprite) : SpriteTexturedParticle(world, x, y, z) {
    init {
        selectSpriteRandomly(sprites)
        motionX = randDouble(-.2, .2)
        motionY = randDouble(-.2, .2)
        motionZ = randDouble(-.2, .2)
        particleScale = .1f
    }

    override fun getMaxAge(): Int {
        return 300
    }

    override fun getRenderType(): IParticleRenderType =
            IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT

    class Factory(val spriteSheet: IAnimatedSprite) : IParticleFactory<BasicParticleType> {
        override fun makeParticle(typeIn: BasicParticleType, world: World, x: Double, y: Double, z: Double, xSpeed: Double, ySpeed: Double, zSpeed: Double): Particle? {
            return MehParticle(world, x, y, z, spriteSheet)
        }
    }
}
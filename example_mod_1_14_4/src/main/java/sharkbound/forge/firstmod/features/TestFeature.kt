package sharkbound.forge.firstmod.features

import com.mojang.datafixers.Dynamic
import net.minecraft.tags.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.GenerationSettings
import net.minecraft.world.gen.feature.Feature
import sharkbound.forge.firstmod.features.config.TestFeatureConfig
import sharkbound.forge.shared.extensions.isAir
import sharkbound.forge.shared.extensions.setState
import sharkbound.forge.shared.extensions.state
import java.util.*

class TestFeature(configFactory: (Dynamic<*>?) -> TestFeatureConfig?) : Feature<TestFeatureConfig>(configFactory) {
    companion object {
        const val REGISTRY_NAME = "test_feature"
    }

    override fun place(world: IWorld, generator: ChunkGenerator<out GenerationSettings>, rand: Random, pos: BlockPos, config: TestFeatureConfig): Boolean {
        var newPos = pos
        var blockstate = world.getBlockState(newPos)
        while ((blockstate.isAir || blockstate.isIn(BlockTags.LEAVES)) && newPos.y > 0) {
            newPos = newPos.down()
            blockstate = world.getBlockState(newPos)
        }

        var i = 0

        for (j in 0..127) {
            val blockpos = newPos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8))
            if (blockpos.up().isAir(world) && blockpos.isAir(world) && blockpos.down().state(world).isSolid && config.state.isValidPosition(world, blockpos)) {
                blockpos.setState(world, config.state, 2)
                ++i
            }
        }

        return i > 0
    }
}
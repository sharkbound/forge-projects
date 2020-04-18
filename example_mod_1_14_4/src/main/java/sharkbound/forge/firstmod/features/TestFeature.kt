package sharkbound.forge.firstmod.features

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.GenerationSettings
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.NoFeatureConfig
import java.util.*

class TestFeature(configFactory: (Dynamic<*>) -> NoFeatureConfig) : Feature<NoFeatureConfig>(configFactory) {
    companion object {
        const val REGISTRY_NAME = "test_feature"
    }

    override fun place(worldIn: IWorld, generator: ChunkGenerator<out GenerationSettings>, rand: Random, pos: BlockPos, config: NoFeatureConfig): Boolean {
        TODO("not implemented")
    }
}
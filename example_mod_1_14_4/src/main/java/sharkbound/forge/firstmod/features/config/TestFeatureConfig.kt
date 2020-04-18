package sharkbound.forge.firstmod.features.config

import com.google.common.collect.ImmutableMap
import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.feature.IFeatureConfig

class TestFeatureConfig(val state: BlockState) : IFeatureConfig {
    companion object {
        const val KEY_STATE = "state"

        /**
         * public static <T> GrassFeatureConfig deserialize(Dynamic<T> p_214707_0_) {
        BlockState blockstate = p_214707_0_.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
        return new GrassFeatureConfig(blockstate);
        }
         */
        fun <T> deserialize(ops: Dynamic<T>): TestFeatureConfig =
                TestFeatureConfig(ops[KEY_STATE].map(BlockState::deserialize).orElse(Blocks.AIR.defaultState))
    }

    override fun <T> serialize(ops: DynamicOps<T>): Dynamic<T> =
            Dynamic(ops, ops.createMap(ImmutableMap.of(ops.createString(KEY_STATE), BlockState.serialize(ops, this.state).value)))
}
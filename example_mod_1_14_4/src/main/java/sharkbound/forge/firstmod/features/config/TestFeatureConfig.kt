package sharkbound.forge.firstmod.features.config

import com.google.common.collect.ImmutableMap
import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.world.gen.feature.IFeatureConfig

class TestFeatureConfig(val chance: Int) : IFeatureConfig {
    companion object {
        const val KEY_CHANCE = "chance"

        /**
         * public static <T> GrassFeatureConfig deserialize(Dynamic<T> p_214707_0_) {
        BlockState blockstate = p_214707_0_.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
        return new GrassFeatureConfig(blockstate);
        }
         */
        fun <T> deserialize(ops: Dynamic<T>): TestFeatureConfig =
                TestFeatureConfig(ops[KEY_CHANCE].asInt(70))
    }

    override fun <T> serialize(ops: DynamicOps<T>): Dynamic<T> =
            Dynamic(ops, ops.createMap(ImmutableMap.of(ops.createString(KEY_CHANCE), ops.createInt(chance))))
}
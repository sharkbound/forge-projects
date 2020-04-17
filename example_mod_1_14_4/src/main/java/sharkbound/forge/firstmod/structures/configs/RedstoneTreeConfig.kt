package sharkbound.forge.firstmod.structures.configs

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.util.ResourceLocation
import net.minecraft.world.gen.feature.IFeatureConfig

class RedstoneTreeConfig(val chance: Int, val template: ResourceLocation) : IFeatureConfig {
    override fun <T : Any?> serialize(ops: DynamicOps<T>): Dynamic<T> =
            Dynamic(ops, ops.createMap(mapOf(
                    ops.createString("chance") to ops.createInt(chance),
                    ops.createString("template") to ops.createString(template.toString())
            )))

    companion object {
        @JvmStatic
        fun deserialize(dynamic: Dynamic<*>): RedstoneTreeConfig =
                RedstoneTreeConfig(dynamic["chance"].asInt(0), ResourceLocation(dynamic["template"].asString("minecraft:empty")))
    }
}
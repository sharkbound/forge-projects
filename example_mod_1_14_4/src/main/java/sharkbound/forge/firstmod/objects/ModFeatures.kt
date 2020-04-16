package sharkbound.forge.firstmod.objects

import net.minecraft.world.gen.feature.Feature
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import sharkbound.forge.firstmod.MOD_ID

@Mod.EventBusSubscriber
object ModFeatures {
    val REGISTRY = DeferredRegister<Feature<*>>(ForgeRegistries.FEATURES, MOD_ID)

    fun init() {
        REGISTRY.register(forgeEventBus)
    }

    val REDSTONE_TREE = REGISTRY.register("redstone_tree")
}
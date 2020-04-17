package sharkbound.forge.firstmod.objects

import net.minecraft.world.gen.feature.Feature
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.structures.RedstoneTreeStructure
import sharkbound.forge.firstmod.structures.configs.RedstoneTreeConfig

@Mod.EventBusSubscriber
object ModFeatures {
    private val REGISTRY = DeferredRegister(ForgeRegistries.FEATURES, MOD_ID)

    fun init() {
        REGISTRY.register(forgeEventBus)
    }

    @JvmStatic
    val REDSTONE_TREE = REGISTRY.register("redstone_tree") {
        println("FACT CALLED")
        RedstoneTreeStructure(RedstoneTreeConfig.Companion::deserialize)
    }
}
package sharkbound.forge.firstmod.proxy

import net.minecraft.block.Blocks
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.GrassFeatureConfig
import net.minecraft.world.gen.placement.FrequencyConfig
import net.minecraft.world.gen.placement.NoiseDependant
import net.minecraft.world.gen.placement.Placement
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.events.ModFeatures
import sharkbound.forge.firstmod.features.config.TestFeatureConfig
import sharkbound.forge.firstmod.networking.Network

open class CommonProxy : Proxy {
    override val world: World
        get() = error("PROXY ERROR: trying to access client world from server")

    override fun init() {
    }

    override fun commonSetup(e: FMLCommonSetupEvent) {
        FirstModItemGroup.init()
        Network.init()
        addFeatures()
    }

    private fun addFeatures() {
        addTestFeature(Biomes.DESERT, TestFeatureConfig(Blocks.BELL.defaultState))
        addTestFeature(Biomes.DESERT_HILLS, TestFeatureConfig(Blocks.BELL.defaultState))
        addTestFeature(Biomes.PLAINS, TestFeatureConfig(Blocks.BIRCH_SAPLING.defaultState))
        addTestFeature(Biomes.FOREST, TestFeatureConfig(Blocks.BIRCH_SAPLING.defaultState))
    }
}

private fun addTestFeature(biome: Biome, config: TestFeatureConfig) {
    biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModFeatures.TEST, config, Placement.COUNT_HEIGHTMAP_DOUBLE, FrequencyConfig(1)))
}
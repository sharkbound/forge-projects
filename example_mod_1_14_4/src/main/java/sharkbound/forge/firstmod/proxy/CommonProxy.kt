package sharkbound.forge.firstmod.proxy

import com.mojang.datafixers.Dynamic
import net.minecraft.nbt.NBTDynamicOps
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.networking.Network
import sharkbound.forge.firstmod.objects.ModFeatures
import sharkbound.forge.firstmod.objects.ModStructurePieceType
import sharkbound.forge.firstmod.structures.RedstoneTree
import sharkbound.forge.firstmod.structures.configs.RedstoneTreeConfig

open class CommonProxy : Proxy {
    override val world: World
        get() = error("PROXY ERROR: trying to access client world from server")

    override fun init() {
        ModFeatures
    }

    private fun addFeatureToBiome(biome: Biome) {
        biome.addFeature(
                GenerationStage.Decoration.SURFACE_STRUCTURES,
                ConfiguredFeature(
                        ModFeatures.REDSTONE_TREE,
                        RedstoneTreeConfig(50, RedstoneTree.REDSTONE_TREE)))
    }

    override fun commonSetup(e: FMLCommonSetupEvent) {
        FirstModItemGroup.init()
        Network.init()

        addFeatureToBiome(Biomes.DESERT)
        addFeatureToBiome(Biomes.PLAINS)

        /*
        this.addSurvivalCamp(Biomes.PLAINS, SurvivalCamp.OAK_SURVIVAL_CAMP);
        this.addSurvivalCamp(Biomes.SUNFLOWER_PLAINS, SurvivalCamp.OAK_SURVIVAL_CAMP);
        this.addSurvivalCamp(Biomes.SAVANNA, SurvivalCamp.ACACIA_SURVIVAL_CAMP);
        this.addSurvivalCamp(Biomes.SAVANNA_PLATEAU, SurvivalCamp.ACACIA_SURVIVAL_CAMP);
        this.addSurvivalCamp(Biomes.FOREST, SurvivalCamp.OAK_SURVIVAL_CAMP);
        this.addSurvivalCamp(Biomes.BIRCH_FOREST, SurvivalCamp.OAK_SURVIVAL_CAMP);
        this.addSurvivalCamp(Biomes.DARK_FOREST, SurvivalCamp.DARK_OAK_SURVIVAL_CAMP);

        Biome.BIOMES.forEach(biome -> {
            ConfiguredFeature<HugeOreFeatureConfig, ? extends Structure<HugeOreFeatureConfig>> hugeOreFeature = ModFeatures.HUGE_ORE.get().func_225566_b_(new HugeOreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 50, 200));
            biome.func_226711_a_(hugeOreFeature);
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, hugeOreFeature.func_227228_a_(Placement.COUNT_DEPTH_AVERAGE.func_227446_a_(new DepthAverageConfig(1, 24, 4))));
        });
    }

    private void addSurvivalCamp(Biome biome, ResourceLocation templateLocation)
    {
        ConfiguredFeature<SurvivalCampConfig, ? extends Structure<SurvivalCampConfig>> survivalCampFeature = ModFeatures.SURVIVAL_CAMP.get().func_225566_b_(new SurvivalCampConfig(Config.COMMON.survivalCampGenerateChance.get(), templateLocation));
        biome.func_226711_a_(survivalCampFeature);
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, survivalCampFeature.func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }*/
    }
}
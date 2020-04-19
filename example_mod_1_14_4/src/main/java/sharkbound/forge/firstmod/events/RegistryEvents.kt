package sharkbound.forge.firstmod.events

import com.mojang.datafixers.Dynamic
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.inventory.container.ContainerType
import net.minecraft.item.Item
import net.minecraft.potion.Effect
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.IFeatureConfig
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.blocks.MehBlock
import sharkbound.forge.firstmod.enchantments.DisplacementEnchantment
import sharkbound.forge.firstmod.entities.MehBlockItemEntity
import sharkbound.forge.firstmod.features.TestFeature
import sharkbound.forge.firstmod.features.config.TestFeatureConfig
import sharkbound.forge.firstmod.gui.container.RepulserContainer
import sharkbound.forge.firstmod.items.EarthShatterer
import sharkbound.forge.firstmod.items.MehBlockItem
import sharkbound.forge.firstmod.items.MehWand
import sharkbound.forge.firstmod.items.Repulser
import sharkbound.forge.firstmod.items.Striker
import sharkbound.forge.firstmod.items.Thrower
import sharkbound.forge.firstmod.objects.ModBlocks
import sharkbound.forge.firstmod.objects.minecraft
import sharkbound.forge.firstmod.potions.ChaosEffect
import sharkbound.forge.shared.util.server
import sharkbound.forge.shared.util.tileEntityRegistryBuilder

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object RegistryEvents {
    @SubscribeEvent
    @JvmStatic
    fun onBlockRegistry(e: RegistryEvent.Register<Block>) {
        e.registry.run {
            register(MehBlock())
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onItemsRegistry(e: RegistryEvent.Register<Item>) {
        e.registry.run {
            register(MehBlockItem(ModBlocks.MEH_BLOCK))
            register(MehWand())
            register(Striker())
            register(EarthShatterer())
            register(Repulser())
            register(Thrower())
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onEffectRegistry(e: RegistryEvent.Register<Effect>) {
        e.registry.run {
            register(ChaosEffect())
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SubscribeEvent
    @JvmStatic
    fun onTileEntityRegistry(e: RegistryEvent.Register<TileEntityType<*>>) {
        e.registry.run {
            register(tileEntityRegistryBuilder({ MehBlockItemEntity() }, ModBlocks.MEH_BLOCK).build(null).setRegistryName(MehBlockItemEntity.REGISTRY_NAME))
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onContainerRegistry(e: RegistryEvent.Register<ContainerType<*>>) {
        e.registry.run {
            register(IForgeContainerType.create { id, inv, data ->
                RepulserContainer(id, inv, inv.player)
            }.setRegistryName(RepulserContainer.REGISTRY_NAME))
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onFeatureRegistry(e: RegistryEvent.Register<Feature<*>>) {
        e.registry.run {
            ModFeatures.TEST = FeatureUtil.registerFeature("$MOD_ID:${TestFeature.REGISTRY_NAME}", TestFeature { TestFeatureConfig.deserialize(it as Dynamic<*>) })
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onEnchantmentRegistry(e: RegistryEvent.Register<Enchantment>) {
        e.registry.run {
            register(DisplacementEnchantment().setRegistryName(DisplacementEnchantment.REGISTRY_NAME))
        }
    }
}

private object FeatureUtil {
    fun <C : IFeatureConfig?, F : Feature<C>?> registerFeature(key: String, value: F): F =
            Registry.register<Feature<*>>(Registry.FEATURE, key, value) as F
}
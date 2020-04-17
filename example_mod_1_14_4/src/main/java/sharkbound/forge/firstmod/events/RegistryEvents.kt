package sharkbound.forge.firstmod.events

import net.minecraft.block.Block
import net.minecraft.inventory.container.ContainerType
import net.minecraft.item.Item
import net.minecraft.potion.Effect
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.Feature
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.blocks.MehBlock
import sharkbound.forge.firstmod.objects.ModBlocks
import sharkbound.forge.firstmod.entities.MehBlockItemEntity
import sharkbound.forge.firstmod.gui.container.RepulserContainer
import sharkbound.forge.firstmod.items.EarthShatterer
import sharkbound.forge.firstmod.items.MehBlockItem
import sharkbound.forge.firstmod.items.MehWand
import sharkbound.forge.firstmod.items.Repulser
import sharkbound.forge.firstmod.items.Striker
import sharkbound.forge.firstmod.items.Thrower
import sharkbound.forge.firstmod.objects.ModFeatures
import sharkbound.forge.firstmod.potions.ChaosEffect
import sharkbound.forge.firstmod.structures.RedstoneTree
import sharkbound.forge.firstmod.structures.RedstoneTreeStructure
import sharkbound.forge.firstmod.structures.configs.RedstoneTreeConfig
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
    fun onFeatureRegistry(e: RegistryEvent.Register<Feature<*>>) {
        e.registry.run {
            register(RedstoneTreeStructure(RedstoneTreeConfig.Companion::deserialize).setRegistryName("redstone_tree"))
//            ModFeatures.REDSTONE_TREE = Registry.register(Registry.FEATURE, "firstmod:" + RedstoneTree.REGISTRY_NAME, RedstoneTreeStructure(RedstoneTreeConfig.Companion::deserialize))
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
}
package sharkbound.forge.firstmod.events

import net.minecraft.block.Block
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.potion.Effect
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ForgeRegistries
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.blocks.FirstBlock
import sharkbound.forge.firstmod.blocks.ModBlocks
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.entities.FirstBlockTileEntity
import sharkbound.forge.firstmod.items.FirstItem
import sharkbound.forge.firstmod.potions.ChaosEffect
import sharkbound.forge.shared.util.tileEntityRegistryBuilder

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object RegistryEvents {
    @SubscribeEvent
    @JvmStatic
    fun onBlockRegistry(e: RegistryEvent.Register<Block>) {
        e.registry.run {
            register(FirstBlock())
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onItemsRegistry(e: RegistryEvent.Register<Item>) {
        e.registry.run {
            register(BlockItem(ModBlocks.FIRST_BLOCK, Item.Properties().group(FirstModItemGroup)).setRegistryName(FirstBlock.REGISTRY_NAME))
            register(FirstItem())
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onEffectRegistry(e: RegistryEvent.Register<Effect>) {
        e.registry.run {
            register(ChaosEffect())
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onTileEntityRegistry(e: RegistryEvent.Register<TileEntityType<*>>) {
        e.registry.run {
            register(tileEntityRegistryBuilder({ FirstBlockTileEntity() }, ModBlocks.FIRST_BLOCK).build(null).setRegistryName(FirstBlockTileEntity.REGISTRY_NAME))
        }
    }
}
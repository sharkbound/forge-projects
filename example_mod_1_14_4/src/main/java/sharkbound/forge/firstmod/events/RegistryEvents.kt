package sharkbound.forge.firstmod.events

import net.minecraft.block.Block
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.blocks.FirstBlock
import sharkbound.forge.firstmod.blocks.ModBlocks
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.items.FirstItem

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
}
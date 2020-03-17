package sharkbound.forge.firstmod

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import sharkbound.forge.firstmod.blocks.FirstBlock
import sharkbound.forge.firstmod.blocks.ModBlocks
import sharkbound.forge.firstmod.data.modEventBus

const val MOD_ID = "firstmod"

@Mod(MOD_ID)
class FirstMod {
    init {
        modEventBus.addListener<FMLCommonSetupEvent> { setup(it) }
    }

    private fun setup(e: FMLCommonSetupEvent) {

    }
}

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object RegistryEvents {
    @SubscribeEvent
    @JvmStatic
    fun onBlockRegistry(e: RegistryEvent.Register<Block>) {
        e.registry.register(FirstBlock())
    }

    @SubscribeEvent
    @JvmStatic
    fun onItemsRegistry(e: RegistryEvent.Register<Item>) {
        e.registry.register(BlockItem(ModBlocks.FIRST_BLOCK, Item.Properties()).setRegistryName(FirstBlock.REGISTRY_NAME))
    }
}
package sharkbound.forge.firstmod.data

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.items.MehBlockItem
import sharkbound.forge.firstmod.items.MehWand

@Mod.EventBusSubscriber
object ModItems {
    @ObjectHolder("$MOD_ID:${MehWand.REGISTRY_NAME}")
    @JvmStatic
    lateinit var MEH_WAND: MehWand

    @ObjectHolder("$MOD_ID:${MehBlockItem.REGISTRY_NAME}")
    @JvmStatic
    lateinit var MEH_BLOCK_ITEM: MehBlockItem
}
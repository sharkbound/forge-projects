package sharkbound.forge.firstmod.creative

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import sharkbound.forge.firstmod.objects.ModBlocks
import sharkbound.forge.firstmod.objects.Tabs


object FirstModItemGroup : ItemGroup(Tabs.FIRST_MOD_TAB) {
    override fun createIcon(): ItemStack =
            ItemStack(ModBlocks.MEH_BLOCK)

    fun init() {

    }
}
package sharkbound.forge.firstmod.creative

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.blocks.ModBlocks

const val ITEM_GROUP_ID = MOD_ID

object FirstModItemGroup : ItemGroup(ITEM_GROUP_ID) {
    override fun createIcon(): ItemStack =
            ItemStack(ModBlocks.FIRST_BLOCK)

    fun init() {

    }
}
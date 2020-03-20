package sharkbound.forge.firstmod.items

import net.minecraft.block.BlockState
import net.minecraft.item.*
import net.minecraft.util.text.ITextComponent
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.data.ModBlocks
import sharkbound.forge.firstmod.interfaces.HasRegistryName
import sharkbound.forge.shared.extensions.color
import sharkbound.forge.shared.util.text

//            register(BlockItem(ModBlocks.MEH_BLOCK, Item.Properties().group(FirstModItemGroup)).setRegistryName(MehBlock.REGISTRY_NAME))
// fixme get this working...
class MehBlockItem : BlockItem(ModBlocks.MEH_BLOCK, Properties().group(FirstModItemGroup)) {
    init {
        setRegistryName("$MOD_ID:mehblockitem")
    }

    override fun getDisplayName(stack: ItemStack): ITextComponent {
        return text(color("&aMeh Block"))
    }

    companion object : HasRegistryName {
        override val REGISTRY_NAME: String
            get() = "mehblockitem"

    }
}
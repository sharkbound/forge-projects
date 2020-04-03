package sharkbound.forge.firstmod.items

import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.util.text.ITextComponent
import sharkbound.forge.firstmod.blocks.MehBlock
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.shared.util.toText

class MehBlockItem(mehBlock: MehBlock) : BlockItem(mehBlock, Properties().group(FirstModItemGroup)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun getDisplayName(stack: ItemStack): ITextComponent {
        return toText("&aMeh Block")
    }

    companion object {
        const val REGISTRY_NAME = "mehblock"
    }
}
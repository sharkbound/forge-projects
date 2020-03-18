package sharkbound.forge.firstmod.items

import net.minecraft.item.Item
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.interfaces.HasRegistryName

class FirstItem : Item(Properties().maxStackSize(1).group(FirstModItemGroup)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    companion object : HasRegistryName {
        override val REGISTRY_NAME: String
            get() = "firstitem"
    }
}
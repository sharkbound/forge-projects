package sharkbound.forge.firstmod.blocks

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import sharkbound.forge.firstmod.interfaces.BlockRegistryName

class FirstBlock : Block(
        Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2f)
                .lightValue(14)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    companion object : BlockRegistryName {
        override val REGISTRY_NAME: String
            get() = "firstblock"
    }
}
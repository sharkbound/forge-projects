package sharkbound.forge.firstmod.blocks

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import sharkbound.forge.firstmod.interfaces.HasRegistryName

class FirstBlock : Block(
        Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2f)
                .lightValue(14)) {
    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun onEntityWalk(worldIn: World, pos: BlockPos, entityIn: Entity) {
//        super.onEntityWalk(worldIn, pos, entityIn)
//        entityIn.tele
    }

    companion object : HasRegistryName {
        override val REGISTRY_NAME: String
            get() = "firstblock"
    }
}
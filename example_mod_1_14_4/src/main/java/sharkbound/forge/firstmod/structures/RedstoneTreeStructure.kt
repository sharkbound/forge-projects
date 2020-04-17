package sharkbound.forge.firstmod.structures

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MutableBoundingBox
import net.minecraft.world.IWorld
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.GenerationSettings
import net.minecraft.world.gen.feature.structure.BuriedTreasureStructure
import net.minecraft.world.gen.feature.structure.Structure
import net.minecraft.world.gen.feature.structure.Structure.IStartFactory
import sharkbound.forge.firstmod.structures.configs.RedstoneTreeConfig
import java.util.*

class RedstoneTreeStructure(configFactory: (Dynamic<*>) -> RedstoneTreeConfig) : Structure<RedstoneTreeConfig>(configFactory) {
    override fun getSize(): Int =
            1

    override fun getStartFactory(): IStartFactory? {
        // debug
        println("START FACTORY")
        return IStartFactory { p_i51165_1_: Structure<*>?,
                               p_i51165_2_: Int,
                               p_i51165_3_: Int,
                               p_i51165_4_: Biome?,
                               p_i51165_5_: MutableBoundingBox?,
                               p_i51165_6_: Int,
                               p_i51165_7_: Long
            ->
            RedstoneTreeStart(p_i51165_1_!!, p_i51165_2_, p_i51165_3_, p_i51165_4_!!, p_i51165_5_!!, p_i51165_6_, p_i51165_7_)
        }
    }

//    override fun place(worldIn: IWorld, generator: ChunkGenerator<out GenerationSettings>, rand: Random, pos: BlockPos, config: RedstoneTreeConfig): Boolean {
//
//    }

    override fun hasStartAt(chunkGen: ChunkGenerator<*>, rand: Random, chunkPosX: Int, chunkPosZ: Int): Boolean {
        //debug
        println("START!!!")
        return true
    }

    override fun getStructureName(): String =
            "redstone_tree"
}
package sharkbound.forge.firstmod.structures

import com.mojang.datafixers.Dynamic
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.feature.structure.Structure
import sharkbound.forge.firstmod.structures.configs.RedstoneTreeConfig
import java.util.*

class RedstoneTreeStructure(configFactory: (Dynamic<*>) -> RedstoneTreeConfig) : Structure<RedstoneTreeConfig>(configFactory) {
    override fun getSize(): Int =
            1

    override fun getStartFactory(): IStartFactory {
        TODO("not implemented")
    }

    override fun hasStartAt(chunkGen: ChunkGenerator<*>, rand: Random, chunkPosX: Int, chunkPosZ: Int): Boolean {
        return rand.nextDouble() < .5
    }

    override fun getStructureName(): String =
            registryName.toString()
}
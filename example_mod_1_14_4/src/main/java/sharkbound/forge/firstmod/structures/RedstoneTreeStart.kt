package sharkbound.forge.firstmod.structures

import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MutableBoundingBox
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.Heightmap
import net.minecraft.world.gen.feature.structure.Structure
import net.minecraft.world.gen.feature.structure.StructureStart
import net.minecraft.world.gen.feature.template.TemplateManager
import sharkbound.forge.firstmod.objects.ModFeatures
import sharkbound.forge.firstmod.structures.configs.RedstoneTreeConfig

class RedstoneTreeStart(structure: Structure<*>, chunkX: Int, chunkZ: Int, biome: Biome, bounds: MutableBoundingBox, ref: Int, seed: Long) :
        StructureStart(structure, chunkX, chunkZ, biome, bounds, ref, seed) {

    override fun init(generator: ChunkGenerator<*>, manager: TemplateManager?, chunkX: Int, chunkZ: Int, biome: Biome?) {
        val posX = chunkX shl 4
        val posZ = chunkZ shl 4
        val height1 = generator.func_222532_b(posX + 3, posZ + 3, Heightmap.Type.OCEAN_FLOOR_WG)
        val height2 = generator.func_222532_b(posX + 13, posZ + 3, Heightmap.Type.OCEAN_FLOOR_WG)
        val height3 = generator.func_222532_b(posX + 3, posZ + 13, Heightmap.Type.OCEAN_FLOOR_WG)
        val height4 = generator.func_222532_b(posX + 13, posZ + 13, Heightmap.Type.OCEAN_FLOOR_WG)
        if (height1 == height2 && height1 == height3 && height1 == height4 && height1 >= generator.seaLevel) {
            val pos = BlockPos(posX + 3, 90, posZ + 3)
            val rotation: Rotation = enumValues<Rotation>().random()
            val config: RedstoneTreeConfig? = generator.getStructureConfig(biome, ModFeatures.REDSTONE_TREE)
            if (config != null && manager != null) {
                components.add(RedstoneTreePiece(manager, pos, rotation, config))
                recalculateStructureSize()
            }
        }
    }
}
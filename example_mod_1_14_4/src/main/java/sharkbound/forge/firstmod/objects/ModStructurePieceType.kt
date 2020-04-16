package sharkbound.forge.firstmod.objects

import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.structure.IStructurePieceType
import sharkbound.forge.firstmod.structures.RedstoneTree

object ModStructurePieceType {
    val REDSTONE_TREE = register(RedstoneTree::Piece, "firstmod:redstone_tree")

    private fun register(type: IStructurePieceType, key: String): IStructurePieceType? {
        return Registry.register(Registry.STRUCTURE_PIECE, ResourceLocation(key), type)
    }
}
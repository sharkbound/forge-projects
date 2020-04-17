package sharkbound.forge.firstmod.objects

import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.structure.IStructurePieceType
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.structures.RedstoneTreePiece

object ModStructurePieceType {
    val REDSTONE_TREE = register("$MOD_ID:redstone_tree", ::RedstoneTreePiece)

    private fun register(key: String, type: IStructurePieceType): IStructurePieceType {
        return Registry.register(Registry.STRUCTURE_PIECE, ResourceLocation(key), type)
    }
}
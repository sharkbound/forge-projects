package sharkbound.forge.firstmod.structures

import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.ResourceLocation
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MutableBoundingBox
import net.minecraft.world.IWorld
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor
import net.minecraft.world.gen.feature.template.JigsawReplacementStructureProcessor
import net.minecraft.world.gen.feature.template.PlacementSettings
import net.minecraft.world.gen.feature.template.TemplateManager
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.objects.ModStructurePieceType
import sharkbound.forge.firstmod.structures.configs.RedstoneTreeConfig
import sun.nio.cs.ext.MacCroatian
import java.util.*

class RedstoneTree {
    companion object {
        val REDSTONE_TREE = ResourceLocation(MOD_ID, "redtree")
        const val REGISTRY_NAME = "redstone_tree"
    }
}

class RedstoneTreePiece(val manager: TemplateManager, val templateLocation: ResourceLocation) : TemplateStructurePiece(ModStructurePieceType.REDSTONE_TREE, 0) {
    var rot = Rotation.CLOCKWISE_90

    constructor(manager: TemplateManager, compound: CompoundNBT) : this(manager, ResourceLocation(compound.getString("Template"))) {
        rot = enumValueOf(compound.getString("Rot"))
    }

    constructor(manager: TemplateManager, pos: BlockPos, rotation: Rotation, config: RedstoneTreeConfig) : this(manager, config.template) {
        rot = rotation
        templatePosition = pos
    }

    init {
        loadTemplate()
    }

    private fun loadTemplate() {
        this.setup(
                manager.getTemplateDefaulted(templateLocation),
                templatePosition,
                PlacementSettings()
                        .setRotation(rot)
                        .setCenterOffset(BlockPos(0, 0, 0))
                        .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
                        .addProcessor(JigsawReplacementStructureProcessor.INSTANCE))
    }

    override fun handleDataMarker(function: String, pos: BlockPos, worldIn: IWorld, rand: Random, sbb: MutableBoundingBox) {
    }

    override fun readAdditional(tag: CompoundNBT) {
        super.readAdditional(tag)
        tag.putString("Rot", rot.name)
        tag.putString("Template", templateLocation.toString())
    }
}
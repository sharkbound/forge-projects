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
import net.minecraft.world.gen.feature.template.Template
import net.minecraft.world.gen.feature.template.TemplateManager
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.objects.ModStructurePieceType
import sharkbound.forge.firstmod.structures.configs.RedstoneTreeConfig
import java.util.*

class RedstoneTree {
    companion object {
        val REDSTONE_TREE = ResourceLocation(MOD_ID, "redtree")
    }

    // (val manager: TemplateManager, val pos: BlockPos, val rot: Rotation, val config: RedstoneTreeConfig)
    class Piece(val manager: TemplateManager, val templateLocation: ResourceLocation) : TemplateStructurePiece(ModStructurePieceType.REDSTONE_TREE!!, 0) {
        val rot: Rotation?
        constructor(manager: TemplateManager, compound: CompoundNBT) : this(manager, ResourceLocation(compound.getString("Template"))) {
            rot =
        }
//        private var templateLocation = config.template

//        constructor(manager: TemplateManager?, compound: CompoundNBT) : super(ModStructurePieceType.REDSTONE_TREE, compound) {
//            rotation = Rotation.valueOf(compound.getString("Rot"))
//            templateLocation = ResourceLocation(compound.getString("Template"))
//            loadTemplate(manager!!)
//        }

        init {

        }

        //        private fun loadTemplate(manager: TemplateManager) {
//            val template: Template = manager.getTemplateDefaulted(templateLocation)
//            val settings = PlacementSettings()
//                    .setRotation(rot)
//                    .setCenterOffset(BlockPos(5, 0, 5))
//                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
//                    .addProcessor(JigsawReplacementStructureProcessor.INSTANCE)
//            this.setup(template, templatePosition, settings)
//        }
//
        override fun handleDataMarker(function: String, pos: BlockPos, worldIn: IWorld, rand: Random, sbb: MutableBoundingBox) {
            TODO("not implemented")
        }
    }
}
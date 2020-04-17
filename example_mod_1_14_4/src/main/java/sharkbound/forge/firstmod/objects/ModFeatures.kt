package sharkbound.forge.firstmod.objects

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.structures.RedstoneTree
import sharkbound.forge.firstmod.structures.RedstoneTreeStructure

@Mod.EventBusSubscriber
object ModFeatures {
    @ObjectHolder("$MOD_ID:${RedstoneTree.REGISTRY_NAME}")
    @JvmStatic
    lateinit var REDSTONE_TREE: RedstoneTreeStructure
}
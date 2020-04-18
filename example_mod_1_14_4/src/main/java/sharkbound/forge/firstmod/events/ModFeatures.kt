package sharkbound.forge.firstmod.events

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.features.TestFeature

@Mod.EventBusSubscriber
object ModFeatures {
    @ObjectHolder("$MOD_ID:${TestFeature.REGISTRY_NAME}")
    @JvmStatic
    lateinit var TEST: TestFeature
}
package sharkbound.forge.firstmod.objects

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.enchantments.DisplacementEnchantment

@Mod.EventBusSubscriber
object ModEnchantments {
    @ObjectHolder("$MOD_ID:${DisplacementEnchantment.REGISTRY_NAME}")
    @JvmStatic
    lateinit var DISPLACEMENT: DisplacementEnchantment
}
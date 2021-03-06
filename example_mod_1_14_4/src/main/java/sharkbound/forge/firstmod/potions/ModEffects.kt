package sharkbound.forge.firstmod.potions

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID

@Mod.EventBusSubscriber
object ModEffects {
    @ObjectHolder("$MOD_ID:chaoseffect")
    @JvmStatic
    lateinit var CHAOS: ChaosEffect
}
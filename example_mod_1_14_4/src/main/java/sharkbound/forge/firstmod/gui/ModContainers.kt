package sharkbound.forge.firstmod.gui

import net.minecraft.client.gui.ScreenManager
import net.minecraft.inventory.container.ContainerType
import net.minecraft.inventory.container.RepairContainer
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ObjectHolder
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.gui.container.RepulserContainer
import sharkbound.forge.firstmod.gui.screen.RepulserScreen

@Mod.EventBusSubscriber
object ModContainers {
    fun init() {
        ScreenManager.registerFactory(REPULSER) { p1, inv, text ->
            RepulserScreen(p1, inv)
        }
    }

    @ObjectHolder("$MOD_ID:${RepulserContainer.REGISTRY_NAME}")
    @JvmStatic
    lateinit var REPULSER: ContainerType<RepulserContainer>
}
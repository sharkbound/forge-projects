package sharkbound.forge.firstmod.gui.screen

import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.gui.container.RepulserContainer
import sharkbound.forge.shared.util.toText

class RepulserScreen(container: RepulserContainer, inv: PlayerInventory) : ContainerScreen<RepulserContainer>(container, inv, toText(TITLE)), INamedContainerProvider {
    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color4f(1f, 1f, 1f, 1f)
        minecraft!!.textureManager.bindTexture(GUI)
        val x = (width / xSize) / 2
        val y = (height / ySize) / 2
        blit(x, y, 0, 0, xSize, ySize)
    }

    override fun render(mx: Int, my: Int, partialTicks: Float) {
        renderBackground()
        super.render(mx, my, partialTicks)
        renderHoveredToolTip(mx, my)
    }

    override fun createMenu(id: Int, inv: PlayerInventory, player: PlayerEntity): Container? {
        return RepulserContainer(id, inv, player)
    }

    override fun getDisplayName(): ITextComponent {
        return toText(TITLE)
    }

    companion object {
        const val TITLE = "&6Repulser Settings"
        val GUI = ResourceLocation(MOD_ID, "textures/gui/repulser.png")
    }
}

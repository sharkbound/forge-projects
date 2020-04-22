package sharkbound.forge.firstmod.gui.screen

import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.client.gui.widget.button.AbstractButton
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import sharkbound.commonutils.extensions.max
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.gui.container.DuplicatorContainer
import sharkbound.forge.firstmod.gui.container.RepulserContainer
import sharkbound.forge.firstmod.items.Repulser
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.send
import sharkbound.forge.shared.util.imageButton
import sharkbound.forge.shared.util.toText

class DuplicatorScreen(container: DuplicatorContainer, val inv: PlayerInventory) : ContainerScreen<DuplicatorContainer>(container, inv, toText(TITLE)) {
    companion object {
        const val TITLE = "&6Duplicator"
        val GUI_TEXTURE = ResourceLocation(MOD_ID, "textures/gui/duplicator/duplicator.png")
//        val BUTTON_TEXTURE = ResourceLocation(MOD_ID, "textures/gui/repulser/button.png")
    }

    val player = inv.player

    override fun mouseClicked(x: Double, y: Double, k: Int): Boolean {
//        addRangeButton.clickIfHovered(x, y)
//        subRangeButton.clickIfHovered(x, y)
        return super.mouseClicked(x, y, k)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color4f(1f, 1f, 1f, 1f)
        minecraft!!.textureManager.bindTexture(GUI_TEXTURE)
        blit(guiLeft, guiTop, 0, 0, xSize, ySize)
    }

    override fun render(mx: Int, my: Int, partialTicks: Float) {
        minecraft!!.textureManager.bindTexture(GUI_TEXTURE)
        super.render(mx, my, partialTicks)
        renderHoveredToolTip(mx, my)
    }
}
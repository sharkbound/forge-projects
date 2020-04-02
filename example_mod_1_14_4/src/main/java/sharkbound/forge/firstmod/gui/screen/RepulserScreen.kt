package sharkbound.forge.firstmod.gui.screen

import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.gui.container.RepulserContainer
import sharkbound.forge.shared.util.toText

class RepulserScreen(container: RepulserContainer, val inv: PlayerInventory) : ContainerScreen<RepulserContainer>(container, inv, toText(TITLE)), INamedContainerProvider {
    val player = inv.player
    val button = addButton(Button1(player, guiTop + 10, guiLeft + 10, 50, 50))

    init {
//        xSize = 200
//        ySize = 200
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color4f(1f, 1f, 1f, 1f)
        minecraft!!.textureManager.bindTexture(GUI)
        blit(guiLeft, guiTop, 0, 0, xSize, ySize)
    }

    override fun render(mx: Int, my: Int, partialTicks: Float) {
        renderBackground()
        super.render(mx, my, partialTicks)
        button.render(mx, my, partialTicks)
        renderHoveredToolTip(mx, my)
    }

    override fun createMenu(id: Int, inv: PlayerInventory, player: PlayerEntity): Container? {
        return RepulserContainer(id, inv, player)
    }

    override fun getDisplayName(): ITextComponent =
            toText(TITLE)

    companion object {
        const val TITLE = "&6Repulser Settings"
        val GUI = ResourceLocation(MOD_ID, "textures/gui/repulser.png")
    }

    class Button1(val player: PlayerEntity, val rx: Int, val ry: Int, width: Int, height: Int) : Button(rx, ry, width, height, "HELLO", { println("HI!") }) {
        companion object {
            val TEXTURE = ResourceLocation(MOD_ID, "textures/gui/button/repulser_button.png")
        }
    }
}

package sharkbound.forge.firstmod.gui.screen

import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.client.gui.widget.button.ImageButton
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
    companion object {
        const val TITLE = "&6Repulser Settings"
        val GUI = ResourceLocation(MOD_ID, "textures/gui/repulser/repulser.png")
        val BUTTON_TEXTURE = ResourceLocation(MOD_ID, "textures/gui/repulser/button.png")
    }

    val player = inv.player

    val button = addButton(ImageButton(0, 0, 65, 35, 0, 0, 36, BUTTON_TEXTURE) {
        println("HI!")
    })

//    override fun keyPressed(x: Int, y: Int, k: Int): Boolean {
//        return super.keyPressed(x, y, k)
//    }

    override fun mouseClicked(x: Double, y: Double, k: Int): Boolean {
        if (button.isHovered) {
            player.closeScreen()
        }
        return super.mouseClicked(x, y, k)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color4f(1f, 1f, 1f, 1f)
        minecraft!!.textureManager.bindTexture(GUI)
        blit(guiLeft, guiTop, 0, 0, xSize, ySize)
    }

    override fun render(mx: Int, my: Int, partialTicks: Float) {
        minecraft!!.textureManager.bindTexture(GUI)
        button.x = guiLeft + 20
        button.y = guiTop + 10
        super.render(mx, my, partialTicks)
        button.render(mx, my, partialTicks)
        renderHoveredToolTip(mx, my)
    }

    override fun createMenu(id: Int, inv: PlayerInventory, player: PlayerEntity): Container? {
        return RepulserContainer(id, inv, player)
    }

    override fun getDisplayName(): ITextComponent =
            toText(TITLE)

//    class Button1(val player: PlayerEntity, val rx: Int, val ry: Int, width: Int, height: Int) : ImageButton(rx, ry, width, height) {
//        override fun renderButton(p_renderButton_1_: Int, p_renderButton_2_: Int, p_renderButton_3_: Float) {
//            minecraft.textureManager.bindTexture(GUI)
//            GlStateManager.color4f(1f, 1f, 1f, 1f)
//            blit(rx, ry, height, 0, width, height)
//            // TODO
//        }
//
//        override fun clicked(p_clicked_1_: Double, p_clicked_3_: Double): Boolean {
//            println("CLICKED")
//            return true
//        }
//
//        override fun onClick(p_onClick_1_: Double, p_onClick_3_: Double) {
//            println("ON CLICK")
//        }
//    }
}

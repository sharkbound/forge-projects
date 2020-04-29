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
import sharkbound.forge.firstmod.gui.container.RepulserContainer
import sharkbound.forge.firstmod.items.Repulser
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.send
import sharkbound.forge.shared.util.imageButton
import sharkbound.forge.shared.util.asText

class RepulserScreen(container: RepulserContainer, val inv: PlayerInventory) : ContainerScreen<RepulserContainer>(container, inv, asText(TITLE)), INamedContainerProvider {
    companion object {
        const val TITLE = "&6Repulser Settings"
        val GUI_TEXTURE = ResourceLocation(MOD_ID, "textures/gui/repulser/repulser.png")
        val BUTTON_TEXTURE = ResourceLocation(MOD_ID, "textures/gui/repulser/button.png")
    }

    val player = inv.player

    val addRangeButton = addButton(imageButton(0, 0, 25, 22, 0, 0, 0, BUTTON_TEXTURE) {
        player.item.stack.let {
            Repulser.setRadius(it, Repulser.radiusOf(it) + 10)
            val newRadius = Repulser.radiusOf(it)
            player.send("&aRadius updated to $newRadius")
            Repulser.sendRadiusPacket(player.uniqueID, newRadius)
        }
    })

    val subRangeButton = addButton(imageButton(0, 0, 25, 22, 25, 0, 0, BUTTON_TEXTURE) {
        player.item.stack.let {
            Repulser.setRadius(it, (Repulser.radiusOf(it) - 10) max 0)
            val newRadius = Repulser.radiusOf(it)
            player.send("&aRadius updated to $newRadius")
            Repulser.sendRadiusPacket(player.uniqueID, newRadius)
        }
    })

    override fun mouseClicked(x: Double, y: Double, k: Int): Boolean {
        addRangeButton.clickIfHovered(x, y)
        subRangeButton.clickIfHovered(x, y)
        return super.mouseClicked(x, y, k)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color4f(1f, 1f, 1f, 1f)
        minecraft!!.textureManager.bindTexture(GUI_TEXTURE)
        blit(guiLeft, guiTop, 0, 0, xSize, ySize)
    }

    override fun render(mx: Int, my: Int, partialTicks: Float) {
        minecraft!!.textureManager.bindTexture(GUI_TEXTURE)
        addRangeButton.setPosition(guiLeft + 20, guiTop + 10)
        subRangeButton.setPosition(guiLeft + 60, guiTop + 10)
        super.render(mx, my, partialTicks)
        addRangeButton.render(mx, my, partialTicks)
        subRangeButton.render(mx, my, partialTicks)
        renderHoveredToolTip(mx, my)
    }

    override fun createMenu(id: Int, inv: PlayerInventory, player: PlayerEntity): Container? {
        return RepulserContainer(id, inv, player)
    }

    override fun getDisplayName(): ITextComponent =
            asText(TITLE)
}

fun AbstractButton.clickIfHovered(x: Double, y: Double) {
    if (isHovered) {
        onClick(x, y)
    }
}

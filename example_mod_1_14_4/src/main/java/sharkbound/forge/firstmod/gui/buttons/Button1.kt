package sharkbound.forge.firstmod.gui.buttons

import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.client.gui.widget.button.AbstractButton
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ResourceLocation
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.data.minecraft
import sharkbound.forge.shared.extensions.send

class Button1(val player: PlayerEntity, val rx: Int, val ry: Int, width: Int, height: Int) : AbstractButton(rx, ry, width, height, "HELLO") {
    override fun onPress() {
        player.send("you clicked me! :D")
    }

    override fun render(x: Int, y: Int, ticks: Float) {
        GlStateManager.color4f(1f, 1f, 1f, 1f)
        minecraft.textureManager.bindTexture(TEXTURE)
        blit(rx, ry, 0, 0, width, height)
    }

    companion object {
        val TEXTURE = ResourceLocation(MOD_ID, "textures/gui/button/repulser_button.png")
    }
}
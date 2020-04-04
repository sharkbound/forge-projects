package sharkbound.forge.shared.util

import net.minecraft.client.gui.widget.button.Button
import net.minecraft.client.gui.widget.button.ImageButton
import net.minecraft.util.ResourceLocation

fun imageButton(x: Int, y: Int, texWidth: Int, texHeight: Int, xTexStart: Int, yTexStart: Int, hoveredYDiff: Int, resource: ResourceLocation, onClick: Button.IPressable): ImageButton =
        ImageButton(x, y, texWidth, texHeight, xTexStart, yTexStart, hoveredYDiff, resource, onClick)
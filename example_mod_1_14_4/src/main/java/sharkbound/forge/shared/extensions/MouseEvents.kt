package sharkbound.forge.shared.extensions

import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.client.event.InputEvent

val InputEvent.MouseScrollEvent.anyMouseKeyDown: Boolean
    get() = isLeftDown || isRightDown || isMiddleDown

enum class ScrollDirection {
    UP, DOWN
}

val InputEvent.MouseScrollEvent.scrollDirection: ScrollDirection
    get() = when {
        scrollDelta < 0 -> ScrollDirection.DOWN
        else -> ScrollDirection.UP
    }
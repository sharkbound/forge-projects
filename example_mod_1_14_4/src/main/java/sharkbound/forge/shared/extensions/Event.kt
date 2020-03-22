package sharkbound.forge.shared.extensions

import net.minecraftforge.eventbus.api.Event
import sharkbound.forge.firstmod.data.logger

fun Event.cancel() {
    if (isCancelable) {
        isCanceled = true
    } else {
        logger.error("tried to cancel un-cancelable event: $javaClass")
    }
}

inline infix fun <T : Event> T.ifNotCanceled(block: T.() -> Unit) {
    if (!isCancelable || !isCanceled) {
        block()
    }
}
package sharkbound.forge.shared.extensions

import net.minecraftforge.eventbus.api.Event

fun Event.cancel() {
    if (isCancelable) {
        isCanceled = true
    } else {
        error("tried to cancel un-cancelable event: $javaClass")
    }
}
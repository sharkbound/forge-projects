package sharkbound.forge.firstmod.events

import sharkbound.commonutils.extensions.max

abstract class TickHandler(val delayTicks: Int, val isRepeating: Boolean = false) {
    enum class State { RUNNING, COMPLETED, SUSPENDED }

    var ticksLeft = delayTicks
    var state: State = State.RUNNING

    abstract fun activate(elapsedTicks: Int)

    fun suspend() {
        state = State.SUSPENDED
    }

    fun cancel() {
        state = State.COMPLETED
    }

    fun isRunning(): Boolean =
            state == State.RUNNING

    open fun tick() {
        if (state != State.RUNNING) return

        ticksLeft = (ticksLeft - 1) max 0
        if (ticksLeft == 0 && state == State.RUNNING) {
            activate(delayTicks)
            state = State.COMPLETED
        }

        if (state == State.COMPLETED && ticksLeft == 0 && isRepeating) {
            ticksLeft = delayTicks
            state = State.RUNNING
        }
    }

    override fun toString(): String {
        return "<${javaClass.simpleName} ticksLeft=$ticksLeft delayTicks=$delayTicks>"
    }
}

class DefaultTickHandler(delayTicks: Int, isRepeating: Boolean, val handler: (Int) -> Unit) : TickHandler(delayTicks, isRepeating) {
    override fun activate(elapsedTicks: Int) {
        handler(elapsedTicks)
    }
}


package sharkbound.forge.firstmod.events

import sharkbound.commonutils.extensions.max

abstract class TickHandler(val delayTicks: Int, val isRepeating: Boolean = false) {
    enum class State { RUNNING, COMPLETED, SUSPENDED }

    var ticksLeft = delayTicks
    var state = State.RUNNING
    var elapsedTicks = 0
    var totalActivates = 0

    abstract fun activate()

    fun suspend() {
        state = State.SUSPENDED
    }

    fun resume() {
        state = State.RUNNING
    }

    fun reset() {
        ticksLeft = delayTicks
        state = State.RUNNING
    }

    fun cancel() {
        state = State.COMPLETED
    }

    val isRunning: Boolean
        get() = state == State.RUNNING

    val isCompleted: Boolean
        get() = state == State.COMPLETED

    open fun tick() {
        if (!isRunning) return

        ticksLeft = (ticksLeft - 1) max 0
        elapsedTicks++
        if (ticksLeft == 0 && state == State.RUNNING) {
            totalActivates++
            activate()
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

class DefaultTickHandler(delayTicks: Int, isRepeating: Boolean, val handler: DefaultTickHandler.() -> Unit) : TickHandler(delayTicks, isRepeating) {
    override fun activate() {
        handler()
    }
}


package sharkbound.forge.firstmod.events.scheduler

import sharkbound.commonutils.extensions.max

abstract class TickHandler(val delayTicks: Int, val isRepeating: Boolean = false, val startDelay: Int = 0) {
    enum class State { RUNNING, COMPLETED, SUSPENDED }

    var ticksLeft = delayTicks
    var state = State.RUNNING
    var elapsedTicks = 0
    var totalActivates = 0
    protected var currentStartDelay = startDelay

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
        currentStartDelay = startDelay
    }

    fun cancel() {
        state = State.COMPLETED
    }

    val isStarted: Boolean
        get() = currentStartDelay <= 0

    val isRunning: Boolean
        get() = isStarted && state == State.RUNNING

    val isCompleted: Boolean
        get() = state == State.COMPLETED

    open fun tick() {
        if (currentStartDelay > 0) currentStartDelay--
        if (!isRunning) return

        ticksLeft = (ticksLeft - 1) max 0
        elapsedTicks++
        if (ticksLeft == 0 && state == State.RUNNING) {
            totalActivates++
            activate()
            state = State.COMPLETED
        }

        if (state == State.COMPLETED && ticksLeft == 0 && isRepeating) {
            reset()
        }
    }

    override fun toString(): String {
        return "<${javaClass.simpleName} ticksLeft=$ticksLeft delayTicks=$delayTicks>"
    }
}

class DefaultTickHandler(delayTicks: Int, isRepeating: Boolean, startDelay: Int = 0, val handler: DefaultTickHandler.() -> Unit) : TickHandler(delayTicks, isRepeating, startDelay) {
    override fun activate() {
        handler()
    }
}


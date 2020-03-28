package sharkbound.forge.firstmod.util

import sharkbound.forge.firstmod.events.*

fun delayTask(delayTicks: Int, handler: (Int) -> Unit): TickHandler =
        run {
            addTickHandler(DefaultTickHandler(delayTicks, isRepeating = false, handler = handler))
        }

fun delayRepeatingTask(delayTicks: Int, handler: (Int) -> Unit): TickHandler =
        run {
            addTickHandler(DefaultTickHandler(delayTicks, isRepeating = true, handler = handler))
        }

fun addTickHandler(tickHandler: TickHandler): TickHandler =
        tickHandler.also {
            ServerEvents.tickHandlers.add(it)
        }

fun runningTickHandlers() =
        ServerEvents.tickHandlers.filter { it.isRunning() }
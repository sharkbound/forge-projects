package sharkbound.forge.firstmod.util

import sharkbound.forge.firstmod.events.DefaultTickHandler
import sharkbound.forge.firstmod.events.ServerEvents
import sharkbound.forge.firstmod.events.TickHandler

fun delayTask(startDelay: Int = 0, delayTicks: Int = 0, handler: TickHandler.() -> Unit): TickHandler =
        run {
            addTickHandler(DefaultTickHandler(delayTicks, isRepeating = false, startDelay = startDelay, handler = handler))
        }

fun delayRepeatingTask(delayTicks: Int, startDelay: Int = 0, handler: TickHandler.() -> Unit): TickHandler =
        run {
            addTickHandler(DefaultTickHandler(delayTicks, isRepeating = true, startDelay = startDelay, handler = handler))
        }

fun addTickHandler(tickHandler: TickHandler): TickHandler =
        tickHandler.also {
            ServerEvents.tickHandlerAddQueue.add(it)
        }

fun runningTickHandlers() =
        ServerEvents.tickHandlers.filter { it.isRunning }
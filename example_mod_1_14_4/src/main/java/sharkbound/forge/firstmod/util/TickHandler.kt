package sharkbound.forge.firstmod.util

import sharkbound.forge.firstmod.events.DefaultTickHandler
import sharkbound.forge.firstmod.events.ServerEvents
import sharkbound.forge.firstmod.events.TickHandler

fun delayTask(delayTicks: Int, handler: TickHandler.() -> Unit): TickHandler =
        run {
            addTickHandler(DefaultTickHandler(delayTicks, isRepeating = false, handler = handler))
        }

fun delayRepeatingTask(delayTicks: Int, handler: TickHandler.() -> Unit): TickHandler =
        run {
            addTickHandler(DefaultTickHandler(delayTicks, isRepeating = true, handler = handler))
        }

fun addTickHandler(tickHandler: TickHandler): TickHandler =
        tickHandler.also {
            ServerEvents.tickHandlerAddQueue.add(it)
        }

fun runningTickHandlers() =
        ServerEvents.tickHandlers.filter { it.isRunning }
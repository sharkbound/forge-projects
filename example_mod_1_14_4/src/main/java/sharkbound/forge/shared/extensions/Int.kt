package sharkbound.forge.shared.extensions

import sharkbound.forge.shared.util.TickUnit

fun Int.ticks(unit: TickUnit): Int =
        unit.applyTo(this)
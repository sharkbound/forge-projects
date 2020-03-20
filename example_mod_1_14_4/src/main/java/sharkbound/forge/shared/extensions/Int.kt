package sharkbound.forge.shared.extensions

import sharkbound.forge.shared.util.TickUnit


fun Int.toTicks(unit: TickUnit): Int =
        unit.applyTo(this)
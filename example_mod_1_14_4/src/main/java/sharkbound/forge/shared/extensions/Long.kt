package sharkbound.forge.shared.extensions

import sharkbound.forge.shared.util.TickUnit


fun Long.toTicks(unit: TickUnit): Long =
        unit.applyTo(this)
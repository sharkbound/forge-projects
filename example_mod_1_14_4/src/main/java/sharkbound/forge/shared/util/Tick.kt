package sharkbound.forge.shared.util

enum class TickUnit(val mul: Int) {
    TICKS(1),
    SECONDS(20),
    MINUTES(20 * 60),
    HOURS(20 * 60 * 60),
    DAYS(20 * 60 * 60 * 24);

    fun applyTo(value: Int): Int =
            value * mul

    fun applyTo(value: Long): Long =
            value * mul
}

fun asTicks(value: Int, unit: TickUnit = TickUnit.TICKS): Int =
        unit.applyTo(value)

fun asTicks(value: Long, unit: TickUnit = TickUnit.TICKS): Long =
        unit.applyTo(value)

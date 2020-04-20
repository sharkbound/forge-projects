package sharkbound.forge.shared.extensions

import net.minecraft.entity.MobEntity
import java.util.*

fun MobEntity.removeAllGoals() {
    (goalSelector.javaClass.getDeclaredField("goals").also { it.isAccessible = true }.get(goalSelector) as? LinkedHashSet<*>)?.clear()
    (goalSelector.javaClass.getDeclaredField("flagGoals").also { it.isAccessible = true }.get(goalSelector) as? EnumMap<*, *>)?.clear()
}
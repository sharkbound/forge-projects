package sharkbound.forge.shared.util

import net.minecraft.util.math.BlockPos

fun blocksInRadius(center: BlockPos, radius: Int) =
        sequence {
            yield(center)
            for (x in -radius..radius) {
                for (y in -radius..radius) {
                    for (z in -radius..radius) {
                        yield(center.add(x, y, z))
                    }
                }
            }
        }
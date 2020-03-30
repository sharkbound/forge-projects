package sharkbound.forge.firstmod.items

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import sharkbound.commonutils.util.randDouble
import sharkbound.commonutils.util.randInt
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.util.delayRepeatingTask
import sharkbound.forge.firstmod.util.delayTask
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.vec3D
import kotlin.contracts.ExperimentalContracts

class EarthShatterer : Item(Properties().group(FirstModItemGroup).maxStackSize(1)) {
    init {
        setRegistryName("earth_shatterer")
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, hand: Hand): ActionResult<ItemStack> {
        if (!world.isServerWorld()) {
            return ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand))
        }

        val blocks = mutableListOf<BlockPos>().apply {
            val verticleRange = 7
            val horizonalRange = 3
            player.rayTraceBlocks(100.0).run {
                (-horizonalRange..horizonalRange).forEach { xo ->
                    (-horizonalRange..horizonalRange).forEach { zo ->
                        (-verticleRange..verticleRange).forEach { yo ->
                            val newPos = hitVec.add(vec3D(xo, yo, zo)).toBlockPos()
                            if (!newPos.isAir(world)) {
                                add(newPos.toImmutable())
                            }
                        }
                    }
                }
            }
        }

        blocks.sortByDescending { it.y }
        delayRepeatingTask(0) {
            if (blocks.isEmpty()) {
                cancel()
                return@delayRepeatingTask
            }

            repeat(randInt(1, 10)) { _ ->
                if (blocks.isNotEmpty()) {
                    blocks[0].let {
                        blocks.remove(it)
                        floatBlock(it, world)
                    }
                }
            }
        }

        return ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand))
    }

    fun floatBlock(pos: BlockPos, world: ServerWorld) {
        world.addFallingBlock(pos.toVec3d(), pos.state(world), vec3D(y = 3), false).let {
            delayTask(5.ticks(TickUnit.SECONDS)) {
                it.setNoGravity(false)
                it.setVel(vec3D(randDouble(-1.5, 1.5), randDouble(-1.5, 1.5), randDouble(-1.5, 1.5)))
            }
        }
        pos.setToAir(world)
    }
}
package sharkbound.forge.firstmod.items

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.UseAction
import net.minecraft.util.ActionResult
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import sharkbound.commonutils.util.randDouble
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.util.delayRepeatingTask
import sharkbound.forge.firstmod.util.delayTask
import sharkbound.forge.shared.extensions.addFallingBlock
import sharkbound.forge.shared.extensions.centerVec
import sharkbound.forge.shared.extensions.get
import sharkbound.forge.shared.extensions.isAir
import sharkbound.forge.shared.extensions.isServerPlayer
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.setToAir
import sharkbound.forge.shared.extensions.setVel
import sharkbound.forge.shared.extensions.state
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.extensions.toActionResult
import sharkbound.forge.shared.extensions.toBlockPos
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.vec3D
import kotlin.contracts.ExperimentalContracts

class EarthShatterer : Item(Properties().group(FirstModItemGroup).maxStackSize(1)) {
    init {
        setRegistryName("earth_shatterer")
    }

    private fun burstVelocity(): Vec3d =
            vec3D(randDouble(-1, 1), 1.5, randDouble(-1, 1))

    override fun getUseAction(stack: ItemStack): UseAction {
        return UseAction.BOW
    }

    override fun getUseDuration(stack: ItemStack): Int {
        return 100
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, hand: Hand): ActionResult<ItemStack> {
        if (!world.isServerWorld()) {
            return player[hand].toActionResult(ActionResultType.PASS)
        }

        player.activeHand = Hand.MAIN_HAND
        return player[hand].toActionResult(ActionResultType.SUCCESS)
    }

    @ExperimentalContracts
    override fun onPlayerStoppedUsing(stack: ItemStack, world: World, player: LivingEntity, timeLeft: Int) {
        if (!world.isServerWorld() || !player.isServerPlayer()) return
        trigger(player, world)
    }

    private fun trigger(player: PlayerEntity, world: ServerWorld) {
        val blocks = getAffectedBlocks(player, world)
        blocks.sortByDescending { it.y }
        val delayBetweenChunks = 0.ticks(TickUnit.TICKS)
        delayRepeatingTask(delayBetweenChunks) {
            if (blocks.isEmpty()) {
                cancel()
                return@delayRepeatingTask
            }

            repeat(1) { _ ->
                if (blocks.isNotEmpty()) {
                    blocks[0].let {
                        blocks.remove(it)
                        floatBlock(it, world)
                    }
                }
            }
        }
    }

    private fun getAffectedBlocks(player: PlayerEntity, world: ServerWorld): MutableList<BlockPos> {
        val v = 20
        val h = 2
        return mutableListOf<BlockPos>().apply {
            player.rayTraceBlocks(100.0).run {
                val center = hitVec.subtract(vec3D(y = 1))
                (-h..h).forEach { xo ->
                    (-h..h).forEach { zo ->
                        (-v..v).forEach { yo ->
                            val newPos = center.add(vec3D(xo, yo, zo)).toBlockPos()
                            if (!newPos.isAir(world)) {
                                add(newPos.toImmutable())
                            }
                        }
                    }
                }
            }
        }
    }

    fun floatBlock(pos: BlockPos, world: ServerWorld) {
        val airTime = 11.ticks(TickUnit.SECONDS)
        world.addFallingBlock(pos.centerVec, pos.state(world), vec3D(y = 3), false).let {
            delayTask(airTime) {
                it.setNoGravity(false)
                it.setVel(burstVelocity())
            }
        }
        pos.setToAir(world)
    }
}
package sharkbound.forge.firstmod.items

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import sharkbound.commonutils.util.randDouble
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

    private var airTime = 11.ticks(TickUnit.SECONDS)
    private var delayBetweenChunks = 5.ticks(TickUnit.TICKS)
    private var verticalRange = 70
    private var horizontalRange = 1
    var tickUseDuration = 100

    private fun burstVelocity(): Vec3d =
            vec3D(randDouble(-1, 1), 1.5, randDouble(-1, 1))

    override fun getUseAction(stack: ItemStack): UseAction {
        return UseAction.BOW
    }

    override fun getUseDuration(stack: ItemStack): Int {
        return tickUseDuration
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, hand: Hand): ActionResult<ItemStack> {
        if (!world.isServerWorld()) {
            return player.getHeldItem(hand).toActionResult(ActionResultType.PASS)
        }

        player.activeHand = Hand.MAIN_HAND
        return player.getHeldItem(hand).toActionResult(ActionResultType.SUCCESS)
    }

    @ExperimentalContracts
    override fun onPlayerStoppedUsing(stack: ItemStack, world: World, player: LivingEntity, timeLeft: Int) {
        if (!world.isServerWorld() || !player.isServerPlayer()) return
        trigger(player, world)
    }

    private fun trigger(player: PlayerEntity, world: ServerWorld) {
        val blocks = mutableListOf<BlockPos>().apply {
            player.rayTraceBlocks(100.0).run {
                (-horizontalRange..horizontalRange).forEach { xo ->
                    (-horizontalRange..horizontalRange).forEach { zo ->
                        (-verticalRange..verticalRange).forEach { yo ->
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
        delayRepeatingTask(delayBetweenChunks) {
            if (blocks.isEmpty()) {
                cancel()
                return@delayRepeatingTask
            }

            repeat(4) { _ ->
                if (blocks.isNotEmpty()) {
                    blocks[0].let {
                        blocks.remove(it)
                        floatBlock(it, world)
                    }
                }
            }
        }
    }

    fun floatBlock(pos: BlockPos, world: ServerWorld) {
        world.addFallingBlock(pos.centerVec, pos.state(world), vec3D(y = 3), false).let {
            delayTask(airTime) {
                it.setNoGravity(false)
                it.setVel(burstVelocity())
            }
        }
        pos.setToAir(world)
    }
}
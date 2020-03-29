package sharkbound.forge.firstmod.items

import net.minecraft.entity.item.FallingBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import sharkbound.commonutils.util.randDouble
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.util.delayTask
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.TickUnit
import kotlin.contracts.ExperimentalContracts

class EarthShatterer : Item(Properties().group(FirstModItemGroup).maxStackSize(1)) {
    init {
        setRegistryName("earth_shatterer")
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, hand: Hand): ActionResult<ItemStack> {
        if (world.isServerWorld()) {
            player.rayTraceBlocks(100.0).run {
                for (xOff in -5..5) {
                    for (zOff in -5..5) {
                        val offsetPos = BlockPos(hitVec.x + xOff, hitVec.y - 1, hitVec.z + zOff)
                        if (offsetPos.isAir(world)) continue
                        val offsetVec = offsetPos.centerVec
                        FallingBlockEntity(world, offsetVec.x + xOff, offsetVec.y, offsetVec.z + zOff, offsetPos.state(world)).run {
                            fallTime = 1
                            setVel(y = 2.0)
                            setNoGravity(true)
                            world.addEntity(this)
                            delayTask(10.ticks(TickUnit.SECONDS)) {
                                setVel(randDouble(-1, 1), 0.0, randDouble(-1, 1))
                                setNoGravity(false)
                            }
                            offsetPos.setToAir(world)
                        }
                    }
                }
            }
        }
        return ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand))
    }
}
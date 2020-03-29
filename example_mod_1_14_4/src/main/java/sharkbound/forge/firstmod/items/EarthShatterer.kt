package sharkbound.forge.firstmod.items

import net.minecraft.block.Blocks
import net.minecraft.entity.item.FallingBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.world.World
import sharkbound.commonutils.util.randDouble
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.shared.extensions.*
import kotlin.contracts.ExperimentalContracts

class EarthShatterer : Item(Properties().group(FirstModItemGroup).maxStackSize(1)) {
    init {
        setRegistryName("earth_shatterer")
    }

    @ExperimentalContracts
    override fun onItemRightClick(world: World, player: PlayerEntity, hand: Hand): ActionResult<ItemStack> {
        if (world.isServerWorld()) {
            player.rayTraceBlocks(100.0).run {
                pos.state(world).let { state ->
                    pos.setToAir(world)
                    FallingBlockEntity(world, hitVec.x, hitVec.y, hitVec.z, state).let {
                        it.fallTime = 1
                        it.setVelocity(randDouble(-2, 2), randDouble(1, 3), randDouble(-2, 2))
                        world.addEntity(it)
                    }
                }
            }
        }
        return ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand))
    }
}
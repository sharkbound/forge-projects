package sharkbound.forge.firstmod.items

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PotionEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.UseAction
import net.minecraft.potion.Effect
import net.minecraft.potion.Effects
import net.minecraft.potion.PotionUtils
import net.minecraft.util.ActionResult
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.Vec3d
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import sharkbound.commonutils.extensions.choice
import sharkbound.commonutils.util.randDouble
import sharkbound.commonutils.util.randInt
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.data.minecraft
import sharkbound.forge.firstmod.events.delayTask
import sharkbound.forge.shared.extensions.component1
import sharkbound.forge.shared.extensions.component2
import sharkbound.forge.shared.extensions.component3
import sharkbound.forge.shared.extensions.dist
import sharkbound.forge.shared.extensions.entitiesInAABB
import sharkbound.forge.shared.extensions.eyePos
import sharkbound.forge.shared.extensions.instance
import sharkbound.forge.shared.extensions.isServerPlayer
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.mul
import sharkbound.forge.shared.extensions.pos
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.setPos
import sharkbound.forge.shared.extensions.setVel
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.extensions.toActionResult
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.toText
import sharkbound.forge.shared.util.vec3D
import kotlin.contracts.ExperimentalContracts

class Thrower : Item(Properties().maxStackSize(1).group(FirstModItemGroup)) {
    companion object {
        const val REGISTRY_NAME = "thrower"
    }

    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun getDisplayName(stack: ItemStack): ITextComponent {
        return toText("&aThrower")
    }

    override fun getUseDuration(stack: ItemStack): Int {
        return 72000
    }

    override fun onItemRightClick(worldIn: World, player: PlayerEntity, hand: Hand): ActionResult<ItemStack> {
        player.activeHand = hand
        return player.item.stack.toActionResult(ActionResultType.SUCCESS)
    }

    override fun getUseAction(stack: ItemStack): UseAction {
        return UseAction.BOW
    }

    val allEffects = Effects::class.java.declaredFields.mapNotNull { it.get(null) as? Effect }

    private fun calcVelocity(hit: Vec3d, player: PlayerEntity, spawnVec: Vec3d): Vec3d {
        val range = 5
        val mobs = player.world.entitiesInAABB<Entity>(AxisAlignedBB(hit.subtract(vec3D(range)), hit.add(vec3D(range)))).filter { it != player && it.isLiving }

        return when {
            mobs.isEmpty() -> hit.subtract(player.eyePos)
            else -> mobs.minBy { it dist player }!!.pos.subtract(spawnVec)
        }.normalize().mul(randDouble(.3, 3.0))
    }

    @ExperimentalContracts
    override fun onUsingTick(stack: ItemStack?, player: LivingEntity?, count: Int) {
        if (!player.isServerPlayer() || !player.world.isServerWorld()) return
        val range = 2

        PotionEntity(player.world, player).let { potion ->
            ItemStack(Items.SPLASH_POTION).let {
                it.orCreateTag.putInt("CustomPotionColor", randInt(0, 0xffffff))
                PotionUtils.appendEffects(it, arrayListOf(allEffects.choice().instance(10.ticks(TickUnit.SECONDS), 3)))
                potion.item = it
            }
            val (x, y, z) = player.eyePos
            val hit = player.rayTraceBlocks(100.0).hitVec
            val spawn = vec3D(x + randDouble(-range, range), y + randDouble(-range, range), z + randDouble(-range, range))
            potion.setPos(spawn)
            potion.setVel(calcVelocity(hit, player, spawn))
            potion.setNoGravity(true)
            player.world.addEntity(potion)
            delayTask(15.ticks(TickUnit.SECONDS)) {
                // TODO add active tracking?
                if (potion.isAlive) potion.remove()
            }
        }
    }
}
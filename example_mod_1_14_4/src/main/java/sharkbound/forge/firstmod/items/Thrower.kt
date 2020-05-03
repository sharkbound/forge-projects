package sharkbound.forge.firstmod.items

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
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import sharkbound.commonutils.extensions.choice
import sharkbound.commonutils.util.randDouble
import sharkbound.commonutils.util.randInt
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.firstmod.events.scheduler.delayTask
import sharkbound.forge.shared.extensions.eyePos
import sharkbound.forge.shared.extensions.instance
import sharkbound.forge.shared.extensions.isServerPlayer
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.mul
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.setPos
import sharkbound.forge.shared.extensions.setVel
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.extensions.toActionResult
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.asText
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
        return asText("&aThrower")
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

    @ExperimentalContracts
    override fun onUsingTick(stack: ItemStack?, player: LivingEntity?, count: Int) {
        if (!player.isServerPlayer() || !player.world.isServerWorld()) return
        val range = 1

        val potion = PotionEntity(player.world, player)
        ItemStack(Items.SPLASH_POTION).let {
            it.orCreateTag.putInt("CustomPotionColor", randInt(0, 0xffffff))
            PotionUtils.appendEffects(it, arrayListOf(allEffects.choice().instance(10.ticks(TickUnit.SECONDS), 3)))
            potion.item = it
        }
        val spawn = player.eyePos.run { vec3D(x + randDouble(-range, range), y + randDouble(-range, range), z + randDouble(-range, range)) }
        val hit = player.rayTraceBlocks(100.0).hitVec
        potion.apply {
            setNoGravity(true)
            setPos(spawn)
            setVel(hit.subtract(player.eyePos)
                    .normalize()
                    .mul(randDouble(.3, 3.0))
                    .rotateYaw(randDouble(-.1, .1).toFloat())
                    .rotatePitch(randDouble(-.1, .1).toFloat()))
        }
        player.world.addEntity(potion)
        delayTask(15.ticks(TickUnit.SECONDS)) {
            if (potion.isAlive) potion.remove()
        }
    }
}
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
import sharkbound.forge.firstmod.creative.FirstModItemGroup
import sharkbound.forge.shared.extensions.component1
import sharkbound.forge.shared.extensions.component2
import sharkbound.forge.shared.extensions.component3
import sharkbound.forge.shared.extensions.eyePos
import sharkbound.forge.shared.extensions.instance
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.mul
import sharkbound.forge.shared.extensions.setVel
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.extensions.toActionResult
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.toText

class Thrower : Item(Properties().maxStackSize(1).group(FirstModItemGroup)) {
    companion object {
        const val REGISTRY_NAME = "thrower"
    }

    init {
        setRegistryName(REGISTRY_NAME)
    }

    override fun getDisplayName(stack: ItemStack): ITextComponent {
        return toText("&aThrower ")
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

    override fun onUsingTick(stack: ItemStack?, player: LivingEntity?, count: Int) {
        if (player == null || player.world.isRemote) return
        val range = 2
        repeat(3) {
            PotionEntity(player.world, player).let { potion ->
                potion.item = PotionUtils.appendEffects(
                        ItemStack(Items.SPLASH_POTION),
                        arrayListOf(allEffects.choice().instance(10.ticks(TickUnit.SECONDS), 3))
                )
                val (x, y, z) = player.eyePos
                potion.setPosition(x + randDouble(-range, range), y + randDouble(-range, range), z + randDouble(-range, range))
                potion.setVel(player.lookVec.normalize().mul(3))
                player.world.addEntity(potion)
            }
        }
    }
}
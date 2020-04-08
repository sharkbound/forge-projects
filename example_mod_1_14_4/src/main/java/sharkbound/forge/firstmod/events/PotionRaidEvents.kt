package sharkbound.forge.firstmod.events

import net.minecraft.entity.projectile.PotionEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.potion.Effect
import net.minecraft.potion.Effects
import net.minecraft.potion.PotionUtils
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.commonutils.extensions.choice
import sharkbound.commonutils.util.randDouble
import sharkbound.forge.shared.extensions.instance
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.playerList

@Mod.EventBusSubscriber
object PotionRaidEvents {
    var ticks = 0
    var allEffects = Effects::class.java.declaredFields.mapNotNull { it.get(null) as? Effect }

    @SubscribeEvent
    @JvmStatic
    fun potionRainTick(e: TickEvent.ServerTickEvent) {
        val range = 1
        ticks++
        for (player in playerList.players) {
            if (player.item isNotItem Items.NETHER_STAR) continue
            PotionEntity(
                    player.world,
                    player.posX + randDouble(-range, range),
                    player.posY - 1,
                    player.posZ + randDouble(-range, range)
            ).let { p ->
                p.item = PotionUtils.appendEffects(
                        ItemStack(Items.SPLASH_POTION),
                        arrayListOf(allEffects.choice().instance(10.ticks(TickUnit.SECONDS), 2))
                )
                player.world.addEntity(p)
            }
        }
    }
}
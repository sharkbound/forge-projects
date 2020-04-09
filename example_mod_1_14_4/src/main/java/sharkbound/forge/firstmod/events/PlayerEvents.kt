package sharkbound.forge.firstmod.events

import net.minecraft.item.Items
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.data.forgeEventBus
import sharkbound.forge.shared.extensions.item

@Mod.EventBusSubscriber
object PlayerEvents {
    init {
        forgeEventBus.register(this)
    }

    @SubscribeEvent
    @JvmStatic
    fun onPlayerTick(e: TickEvent.PlayerTickEvent) {
    }
}
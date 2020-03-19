package sharkbound.forge.firstmod.events

import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.data.forgeEventBus
import sharkbound.forge.firstmod.items.FirstItem
import sharkbound.forge.shared.extensions.heldItemInfo
import sharkbound.forge.shared.extensions.send

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
object PlayerEvents {
    init {
        forgeEventBus.register(this)
    }

    @SubscribeEvent
    fun tick(e: TickEvent.PlayerTickEvent) {
        e.player.send(e.player.heldItemInfo.asType<FirstItem>())
    }
}
package sharkbound.forge.firstmod.networking

import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.network.NetworkRegistry
import net.minecraftforge.fml.network.simple.SimpleChannel
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.networking.packets.MehWandModeSwitchPacket

object Network {
    lateinit var channel: SimpleChannel
    val mehWandModeSwitch = nextId()

    var id = 0
    fun nextId() =
            ++id

    @Suppress("INACCESSIBLE_TYPE")
    fun init() {
        channel = NetworkRegistry.newSimpleChannel(ResourceLocation(MOD_ID, "firstmod"), { "1.0" }, { true }, { true })
        channel.registerMessage(
                mehWandModeSwitch,
                MehWandModeSwitchPacket::class.java,
                { packet, buf -> packet.write(buf) },
                { MehWandModeSwitchPacket(it) },
                { packet, ctx -> packet.handle(ctx) })
    }
}
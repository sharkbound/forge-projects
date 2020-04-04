package sharkbound.forge.firstmod.networking

import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.network.NetworkRegistry
import net.minecraftforge.fml.network.simple.SimpleChannel
import sharkbound.forge.firstmod.MOD_ID
import sharkbound.forge.firstmod.networking.packets.MehWandModeSwitchPacket
import sharkbound.forge.firstmod.networking.packets.RepulserRadiusChangePacket

object Network {
    private var id = 0
    lateinit var channel: SimpleChannel
    private val mehWandModeSwitch = ++id
    private val repulserRadiusChange = ++id

    @Suppress("INACCESSIBLE_TYPE")
    fun init() {
        channel = NetworkRegistry.newSimpleChannel(ResourceLocation(MOD_ID, "firstmod"), { "1.0" }, { true }, { true })
        channel.registerMessage(
                mehWandModeSwitch,
                MehWandModeSwitchPacket::class.java,
                { packet, buf -> packet.write(buf) },
                { MehWandModeSwitchPacket(it) },
                { packet, ctx -> packet.handle(ctx) })

        channel.registerMessage(
                repulserRadiusChange,
                RepulserRadiusChangePacket::class.java,
                { packet, buf -> packet.write(buf) },
                { RepulserRadiusChangePacket(it) },
                { packet, ctx -> packet.handle(ctx) })
    }
}
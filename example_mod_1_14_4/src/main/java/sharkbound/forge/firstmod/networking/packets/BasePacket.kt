package sharkbound.forge.firstmod.networking.packets

import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier

interface BasePacket {
    fun write(buf: PacketBuffer)
    fun handle(ctx: Supplier<NetworkEvent.Context>)
}
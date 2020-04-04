package sharkbound.forge.firstmod.networking.packets

import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.network.NetworkEvent
import sharkbound.forge.firstmod.items.MehWand
import sharkbound.forge.firstmod.items.Repulser
import sharkbound.forge.shared.extensions.item
import sharkbound.forge.shared.extensions.mainHand
import java.util.*
import java.util.function.Supplier

private val modes = enumValues<MehWand.Mode>()

class RepulserRadiusChangePacket(val playerId: UUID, val newRadius: Int) : BasePacket {
    constructor(buf: PacketBuffer) : this(
            buf.readUniqueId(),
            buf.readInt()
    )

    override fun write(buf: PacketBuffer) {
        buf.run {
            writeUniqueId(playerId)
            writeInt(newRadius)
        }
    }

    override fun handle(ctx: Supplier<NetworkEvent.Context>) {
        ctx.get().run {
            enqueueWork {
                sender?.let {
                    Repulser.setRadius(it.item.stack, newRadius)
                }
            }
        }
    }
}
package sharkbound.forge.firstmod.networking.packets

import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.network.NetworkEvent
import sharkbound.forge.firstmod.items.MehWand
import sharkbound.forge.shared.extensions.mainHand
import java.util.*
import java.util.function.Supplier

private val modes = enumValues<MehWand.Mode>()

class MehWandModeSwitchPacket(val playerId: UUID, val newMode: MehWand.Mode) : BasePacket {
    constructor(buf: PacketBuffer) : this(
            buf.readUniqueId(),
            buf.readInt().let { mode -> modes.first { it.ordinal == mode } }
    )

    override fun write(buf: PacketBuffer) {
        buf.run {
            writeUniqueId(playerId)
            writeInt(newMode.ordinal)
        }
    }

    override fun handle(ctx: Supplier<NetworkEvent.Context>) {
        ctx.get().run {
            enqueueWork {
                sender?.let {
                    MehWand.setMode(it.mainHand, newMode)
                }
            }
        }
    }
}
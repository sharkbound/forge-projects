package sharkbound.forge.firstmod.util

import net.minecraftforge.fml.LogicalSide
import sharkbound.forge.firstmod.objects.proxy

val isClient: Boolean
    get() = proxy.side == LogicalSide.CLIENT

val isServer: Boolean
    get() = proxy.side == LogicalSide.SERVER
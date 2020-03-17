package sharkbound.forge.firstmod.proxy

import sharkbound.forge.firstmod.minecraft
import net.minecraft.world.World

class ClientProxy : Proxy {
    override val world: World
        get() = minecraft.world
}
package sharkbound.forge.firstmod.proxy

import net.minecraft.world.World
import sharkbound.forge.firstmod.data.minecraft

class ClientProxy : Proxy {
    override fun init() {

    }

    override val world: World
        get() = minecraft.world
}
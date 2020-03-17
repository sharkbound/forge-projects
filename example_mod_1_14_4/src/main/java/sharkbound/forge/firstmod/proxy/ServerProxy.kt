package sharkbound.forge.firstmod.proxy

import net.minecraft.world.World

class ServerProxy : Proxy {
    override val world: World
        get() = error("PROXY ERROR: trying to access client world from server")
}
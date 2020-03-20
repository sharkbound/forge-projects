package sharkbound.forge.firstmod.particles

import net.minecraft.client.particle.*
import net.minecraft.network.PacketBuffer
import net.minecraft.particles.*
import net.minecraft.world.World
import java.rmi.registry.Registry

class MehParticle(val world: World, val x: Double, val y: Double, val z: Double) : BasicParticleType(true) {

}
package sharkbound.forge.firstmod.particles.data

import com.mojang.brigadier.StringReader
import net.minecraft.network.PacketBuffer
import net.minecraft.particles.IParticleData
import net.minecraft.particles.ParticleType
import net.minecraft.util.math.Vec3d
import sharkbound.forge.firstmod.objects.ModParticles
import sharkbound.forge.firstmod.objects.forgeParticleRegistry

class MehBoltParticleData(tx: Double, ty: Double, tz: Double) : IParticleData {
    val target = Vec3d(tx, ty, tz)

    constructor(target: Vec3d) : this(target.x, target.y, target.z)

    override fun getParameters(): String {
        return target.run { "${forgeParticleRegistry.getKey(type)} $x $y $z" }
    }

    override fun getType(): ParticleType<*> {
        return ModParticles.MEH_BOLT.get() ?: error("MEH_BOLT was null in `MehBoltParticleData.getType()`")
    }

    override fun write(buffer: PacketBuffer) {
        buffer.writeDouble(target.x)
        buffer.writeDouble(target.y)
        buffer.writeDouble(target.z)
    }

    class DESERIALIZER : IParticleData.IDeserializer<MehBoltParticleData> {
        override fun deserialize(particleTypeIn: ParticleType<MehBoltParticleData>, reader: StringReader): MehBoltParticleData {
            reader.expect(' ')
            val x = reader.readDouble()
            reader.expect(' ')
            val y = reader.readDouble()
            reader.expect(' ')
            val z = reader.readDouble()
            return MehBoltParticleData(x, y, z)
        }

        override fun read(particleTypeIn: ParticleType<MehBoltParticleData>, buffer: PacketBuffer): MehBoltParticleData {
            return MehBoltParticleData(buffer.readDouble(), buffer.readDouble(), buffer.readDouble())
        }
    }
}
package sharkbound.forge.firstmod.entities

import net.minecraft.particles.ParticleTypes
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntity
import sharkbound.commonutils.extensions.ifNotNull
import sharkbound.forge.firstmod.data.*
import sharkbound.forge.firstmod.particles.data.MehBoltParticleData
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.*
import kotlin.contracts.ExperimentalContracts

class MehBlockItemEntity : TileEntity(ModBlocks.MEH_BLOCK_TILE_ENTITY), ITickableTileEntity {
    private val incr = Incrementer(1.ticks(TickUnit.SECONDS))

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @ExperimentalContracts
    override fun tick() {
        world.ifNotNull { w ->
            if (w.isClientWorld() && incr.next()) {
                pos.centerVec.run {
//                    w.addParticle(MehBoltParticleData(add(newVec3D(y = 5.0))), x, y, z, 0.0, 0.0, 0.0)
                    w.addParticle(MehBoltParticleData(add(newVec3D(y = 10))), x, y, z, 0.0, 0.0, 0.0)
                }
            }
        }
    }

    companion object {
        const val REGISTRY_NAME = "mehblock"
    }
}
package sharkbound.forge.firstmod.entities

import net.minecraft.particles.ParticleTypes
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntity
import sharkbound.commonutils.extensions.ifNotNull
import sharkbound.forge.firstmod.data.*
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.*
import kotlin.contracts.ExperimentalContracts

class MehBlockItemEntity : TileEntity(ModBlocks.MEH_BLOCK_TILE_ENTITY), ITickableTileEntity {
    private val incr = Incrementer(1.toTicks(TickUnit.SECONDS))

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @ExperimentalContracts
    override fun tick() {
        world.ifNotNull { w ->
            if (w.isClientWorld() && incr.next()) {
                pos.centerVec.run {
                    w.addParticle(ModParticles.MEH.get(), x, y, z, 0.0, 0.0, 0.0)
                }
            }
        }
    }

    companion object {
        const val REGISTRY_NAME = "mehblock"
    }
}
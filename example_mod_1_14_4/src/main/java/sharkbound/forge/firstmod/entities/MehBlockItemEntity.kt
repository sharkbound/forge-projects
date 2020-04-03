package sharkbound.forge.firstmod.entities

import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntity
import sharkbound.commonutils.extensions.ifNotNull
import sharkbound.forge.firstmod.data.ModBlocks
import sharkbound.forge.firstmod.data.ModParticles
import sharkbound.forge.shared.extensions.centerVec
import sharkbound.forge.shared.extensions.isClientWorld
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.Incrementer
import sharkbound.forge.shared.util.TickUnit
import kotlin.contracts.ExperimentalContracts

class MehBlockItemEntity : TileEntity(ModBlocks.MEH_BLOCK_TILE_ENTITY), ITickableTileEntity {
    private val incr = Incrementer(1.ticks(TickUnit.SECONDS))

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @ExperimentalContracts
    override fun tick() {
        world.ifNotNull { w ->
            if (w.isClientWorld() && incr.next()) {
                pos.centerVec.run {
                    w.addParticle(ModParticles.MEH.get(), x, y, z, 0.0, 0.0, 0.0)
//                    w.addParticle(MehBoltParticleData(add(newVec3D(y = 10))), x, y, z, 0.0, 0.0, 0.0)
                }
            }
        }
    }

    companion object {
        const val REGISTRY_NAME = "mehblock"
    }
}
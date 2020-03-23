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

    @ExperimentalContracts
    override fun tick() {
        world.ifNotNull { w ->
            if (w.isServerWorld() && incr.next()) {
                pos.centerVec.run {
                    // debug
                    logger.error(ModParticles.MEH.get())
                    w.addParticle(ModParticles.MEH.get(), x, y + 3, z, 0.0, 0.0, 0.0)
                }
            }
        }
    }

    companion object {
        const val REGISTRY_NAME = "mehblock"
    }
}
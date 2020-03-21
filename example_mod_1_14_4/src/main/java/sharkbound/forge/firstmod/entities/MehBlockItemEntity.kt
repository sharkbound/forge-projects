package sharkbound.forge.firstmod.entities

import net.minecraft.particles.ParticleTypes
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntity
import sharkbound.commonutils.extensions.ifNotNull
import sharkbound.forge.firstmod.data.ModBlocks
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
                    w.particle(ParticleTypes.FLAME, x, y + 1.5, z, speed = .1)
                }
            }
        }
    }

    companion object {
        const val REGISTRY_NAME = "mehblock"
    }
}
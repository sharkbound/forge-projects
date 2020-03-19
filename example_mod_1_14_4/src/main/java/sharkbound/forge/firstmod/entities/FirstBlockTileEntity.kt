package sharkbound.forge.firstmod.entities

import net.minecraft.particles.ParticleTypes
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntity
import sharkbound.commonutils.extensions.ifNotNull
import sharkbound.forge.firstmod.blocks.ModBlocks
import sharkbound.forge.firstmod.interfaces.HasRegistryName
import sharkbound.forge.shared.extensions.*
import sharkbound.forge.shared.util.*
import kotlin.contracts.ExperimentalContracts

class FirstBlockTileEntity : TileEntity(ModBlocks.FIRST_BLOCK_TILE_ENTITY), ITickableTileEntity {
    private val incr = Incrementer(ticks(3, TickUnit.SECONDS))

    @ExperimentalContracts
    override fun tick() {
        world.ifNotNull {
            if (it.isServerWorld() && incr.next()) {
                val v = pos.centerVec
                it.particle(ParticleTypes.FLAME, v.x, v.y, v.z, speed = .1)
            }
        }
    }

    companion object : HasRegistryName {
        override val REGISTRY_NAME: String
            get() = "firstblock"
    }
}
package sharkbound.forge.firstmod.events.player

import net.minecraft.block.Blocks
import net.minecraft.tileentity.LockableLootTileEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.gen.feature.template.PlacementSettings
import net.minecraft.world.server.ServerWorld
import net.minecraft.world.storage.loot.LootTables
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide
import net.minecraftforge.fml.common.Mod
import sharkbound.commonutils.rand
import sharkbound.forge.firstmod.events.scheduler.delayRepeatingTask
import sharkbound.forge.firstmod.events.scheduler.delayTask
import sharkbound.forge.firstmod.objects.forgeEventBus
import sharkbound.forge.firstmod.modResourceId
import sharkbound.forge.firstmod.objects.Flags
import sharkbound.forge.shared.extensions.eyePos
import sharkbound.forge.shared.extensions.hasNBTKey
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.plus
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.setToAir
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.createArrow
import kotlin.contracts.ExperimentalContracts

@Mod.EventBusSubscriber
object PlayerEvents {
    init {
        forgeEventBus.register(this)
    }

    @SubscribeEvent
    @JvmStatic
    fun onPlayerTick(e: TickEvent.PlayerTickEvent) {
    }

    @ExperimentalContracts
    @SubscribeEvent
    @JvmStatic
    fun playerRightClickItem(e: PlayerInteractEvent.RightClickItem) {
        if (e.side != LogicalSide.SERVER) return

        val world = e.world
        if (!world.isServerWorld()) return

        when {
            e.itemStack hasNBTKey Flags.GLASS_BOX_SPAWNER -> dungeonSpawner(e, world)
            e.itemStack hasNBTKey Flags.ARROW_SPAWNER_KEY -> arrowSpawner(e, world)
        }
    }
}

private fun arrowSpawner(e: PlayerInteractEvent.RightClickItem, world: ServerWorld) {
    val arrowAmount = 50
    val player = e.player
    val spawnPos = player.eyePos + player.lookVec
    val dir = player.lookVec
    delayTask(10.ticks(TickUnit.SECONDS)) {
        for (_i in 1..arrowAmount) {
            world.addEntity(createArrow(world, spawnPos).apply {
                dir.run<Vec3d, Unit> { shoot(x, y, z, 2f, 50f) }
                persistentData.putBoolean("removeonimpact", true)
            })
        }
    }
}

@ExperimentalContracts
private fun dungeonSpawner(e: PlayerInteractEvent.RightClickItem, world: ServerWorld) {
    val path = modResourceId("glass_box")
    val template = world.structureTemplateManager.getTemplate(path) ?: return
    val settings = PlacementSettings()
    val pos = e.player.rayTraceBlocks(30.0).pos
    template.addBlocksToWorld(e.world, pos, settings)
    for (value in template.func_215381_a(pos, settings, Blocks.STRUCTURE_BLOCK)) {
        when (value.nbt["metadata"]?.string) {
            "loot" -> {
                LockableLootTileEntity.setLootTable(e.world, e.world.rand, value.pos.down(), LootTables.CHESTS_SIMPLE_DUNGEON)
                value.pos.setToAir(e.world)
            }

        }
    }
}


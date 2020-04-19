package sharkbound.forge.firstmod.events

import net.minecraft.block.Blocks
import net.minecraft.item.Items
import net.minecraft.tileentity.LockableLootTileEntity
import net.minecraft.world.gen.feature.template.PlacementSettings
import net.minecraft.world.storage.loot.LootTables
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide
import net.minecraftforge.fml.common.Mod
import sharkbound.commonutils.rand
import sharkbound.forge.firstmod.objects.forgeEventBus
import sharkbound.forge.firstmod.modResourceId
import sharkbound.forge.firstmod.objects.Flags
import sharkbound.forge.shared.extensions.hasNBTKey
import sharkbound.forge.shared.extensions.isItem
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.extensions.rayTraceBlocks
import sharkbound.forge.shared.extensions.send
import sharkbound.forge.shared.extensions.setToAir
import sharkbound.forge.shared.extensions.tileEntity
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
        if (e.itemStack hasNBTKey Flags.DUNGEON_SPAWNER_KEY && world.isServerWorld()) {
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
    }
}
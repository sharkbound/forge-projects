package sharkbound.forge.firstmod.entities

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.inventory.container.Container
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.LockableTileEntity
import net.minecraft.util.NonNullList
import net.minecraft.util.text.ITextComponent
import sharkbound.commonutils.extensions.len
import sharkbound.forge.firstmod.gui.container.DuplicatorContainer
import sharkbound.forge.firstmod.objects.ModBlocks
import sharkbound.forge.shared.extensions.ticks
import sharkbound.forge.shared.util.Incrementer
import sharkbound.forge.shared.util.TickUnit
import sharkbound.forge.shared.util.toText

class DuplicatorBlockTileEntity : LockableTileEntity(ModBlocks.DUPLICATOR_TILE_ENTITY) {
    private val incr = Incrementer(1.ticks(TickUnit.SECONDS))
    val items = NonNullList.withSize(2, ItemStack.EMPTY)

    companion object {
        const val REGISTRY_NAME = "duplicator"
    }

    override fun getStackInSlot(index: Int): ItemStack {
        return items[index - 36]
    }

    override fun removeStackFromSlot(index: Int): ItemStack =
            ItemStackHelper.getAndRemove(items, index)

    override fun decrStackSize(index: Int, count: Int): ItemStack =
            ItemStackHelper.getAndSplit(items, index, count)

    override fun isEmpty(): Boolean {
        return items.all { it.isEmpty }
    }

    override fun getDefaultName(): ITextComponent {
        return toText("&eDuplicator")
    }

    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
        println("$index $stack $items")
        items[index] = stack
    }

    override fun clear() {
        items.clear()
    }

    override fun getSizeInventory(): Int {
        return items.len
    }

    override fun isUsableByPlayer(player: PlayerEntity): Boolean =
            true

    override fun createMenu(id: Int, player: PlayerInventory): Container {
        // todo get extra data working
        return DuplicatorContainer(id, player, player.player)
    }
}
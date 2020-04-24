package sharkbound.forge.firstmod.entities

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.inventory.container.Container
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.LockableTileEntity
import net.minecraft.util.NonNullList
import net.minecraft.util.text.ITextComponent
import sharkbound.commonutils.extensions.len
import sharkbound.forge.firstmod.gui.container.DuplicatorContainer
import sharkbound.forge.firstmod.objects.ModBlocks
import sharkbound.forge.firstmod.objects.proxy
import sharkbound.forge.shared.extensions.isServerWorld
import sharkbound.forge.shared.util.classes.IndexVar
import sharkbound.forge.shared.util.toText
import kotlin.contracts.ExperimentalContracts

class DuplicatorBlockTileEntity : LockableTileEntity(ModBlocks.DUPLICATOR_TILE_ENTITY), ITickableTileEntity {
    companion object {
        const val REGISTRY_NAME = "duplicator"
        private fun createDefaultItemList(): NonNullList<ItemStack> =
                NonNullList.withSize(2, ItemStack.EMPTY)
    }

    var items = createDefaultItemList()
    var container: DuplicatorContainer? = null
    var player: PlayerEntity? = null

    var input by IndexVar(0, items)
    var output by IndexVar(1, items)

    @ExperimentalContracts
    override fun tick() {
        if (world.isServerWorld() && !input.isEmpty && output.isEmpty) {
            output = input.copy()
            updateSlots()
        }
    }

    override fun getStackInSlot(index: Int): ItemStack {
        return items[index]
    }

    override fun removeStackFromSlot(index: Int): ItemStack {
        return ItemStackHelper.getAndRemove(items, index)
    }

    override fun decrStackSize(index: Int, count: Int): ItemStack {
        return ItemStackHelper.getAndSplit(items, index, count)
    }

    override fun isEmpty(): Boolean {
        return items.all { it.isEmpty }
    }

    override fun getDefaultName(): ITextComponent {
        return toText("&eDuplicator")
    }

    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
        if (index == 0) {
            input = stack
            output = stack.copy()
            updateSlots()
        }
    }

    private fun updateSlots() {
        container?.detectAndSendChanges()
        markDirty()
    }

    override fun read(compound: CompoundNBT) {
        super.read(compound)
        items = createDefaultItemList()
        ItemStackHelper.loadAllItems(compound, items)
    }

    override fun write(compound: CompoundNBT): CompoundNBT {
        return compound.apply {
            super.write(this)
            ItemStackHelper.saveAllItems(this, items)
        }
    }

    override fun clear() {
        items.clear()
    }

    override fun getSizeInventory(): Int {
        return items.len
    }

    override fun isUsableByPlayer(player: PlayerEntity): Boolean =
            true

    override fun createMenu(id: Int, playerInv: PlayerInventory): Container {
        return DuplicatorContainer(id, playerInv, playerInv.player, this).also {
            container = it
            player = playerInv.player
        }
    }
}
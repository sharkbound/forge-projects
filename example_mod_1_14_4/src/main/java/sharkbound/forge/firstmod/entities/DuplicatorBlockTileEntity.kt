package sharkbound.forge.firstmod.entities

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.inventory.container.Container
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.LockableLootTileEntity
import net.minecraft.util.NonNullList
import net.minecraft.util.text.ITextComponent
import net.minecraftforge.fml.network.NetworkRegistry
import sharkbound.commonutils.extensions.len
import sharkbound.forge.firstmod.gui.container.DuplicatorContainer
import sharkbound.forge.firstmod.objects.ModBlocks
import sharkbound.forge.shared.extensions.isServer
import sharkbound.forge.shared.extensions.name
import sharkbound.forge.shared.util.classes.IndexVar
import sharkbound.forge.shared.util.toText
import kotlin.contracts.ExperimentalContracts

class DuplicatorBlockTileEntity : LockableLootTileEntity(ModBlocks.DUPLICATOR_TILE_ENTITY), ITickableTileEntity {
    companion object {
        const val REGISTRY_NAME = "duplicator"
        private fun createDefaultItemList(): NonNullList<ItemStack> =
                NonNullList.withSize(2, ItemStack.EMPTY)
    }

    var tileItems = createDefaultItemList()
    var container: DuplicatorContainer? = null
    var player: PlayerEntity? = null

    var input by IndexVar(0, tileItems)
    var output by IndexVar(1, tileItems)

    var ticks = 0

    @ExperimentalContracts
    override fun tick() {
        // ticks used for logging inv
        ticks++
        if (ticks % 20 == 0) {
            // debug info
            println("server=${world.isServer()} - ${tileItems.joinToString(", ") { it?.name ?: "NONAME" }}")
        }
        if (!input.isEmpty && output.isEmpty) {
            output = input.copy()
            updateSlots()
        }
    }

    override fun getStackInSlot(index: Int): ItemStack {
        return tileItems[index]
    }

    override fun removeStackFromSlot(index: Int): ItemStack {
        return ItemStackHelper.getAndRemove(tileItems, index)
    }

    override fun decrStackSize(index: Int, count: Int): ItemStack {
        return ItemStackHelper.getAndSplit(tileItems, index, count)
    }

    override fun isEmpty(): Boolean {
        return tileItems.all { it.isEmpty }
    }

    override fun getDefaultName(): ITextComponent {
        return toText("&eDuplicator")
    }

    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
        if (index == 0) {
            input = stack.copy()
            output = stack.copy()
            updateSlots()
        }
    }

    private fun updateSlots() {
        markDirty()
        container?.detectAndSendChanges()
    }

    override fun read(compound: CompoundNBT) {
        super.read(compound)
        tileItems = createDefaultItemList()
        ItemStackHelper.loadAllItems(compound, tileItems)
    }

    override fun write(compound: CompoundNBT): CompoundNBT {
        return compound.apply {
            super.write(this)
            ItemStackHelper.saveAllItems(this, tileItems)
        }
    }

    override fun clear() {
        tileItems.clear()
    }

    override fun getItems(): NonNullList<ItemStack> {
        return tileItems
    }

    override fun setItems(itemsIn: NonNullList<ItemStack>) {
        tileItems = itemsIn
    }

    override fun getSizeInventory(): Int {
        return tileItems.len
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
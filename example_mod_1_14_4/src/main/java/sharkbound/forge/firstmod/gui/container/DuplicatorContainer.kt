package sharkbound.forge.firstmod.gui.container

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.Slot
import net.minecraft.item.ItemStack
import sharkbound.forge.firstmod.gui.ModContainers

class DuplicatorContainer(var id: Int, val playerInv: PlayerInventory, val player: PlayerEntity, val extraInv: IInventory) : Container(ModContainers.DUPLICATOR, id) {
    constructor(id: Int, playerInv: PlayerInventory, player: PlayerEntity) : this(id, playerInv, player, Inventory(2))

    init {
        for (i in 0..2) {
            for (j in 0..8) {
                addSlot(Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
            }
        }

        for (k in 0..8) {
            addSlot(Slot(playerInv, k, 8 + k * 18, 142))
        }

        addSlot(Slot(extraInv, 0, 10, 10))
        addSlot(object : Slot(extraInv, 1, 30, 10) {
            override fun isItemValid(stack: ItemStack) =
                    false
        })
    }

    override fun canInteractWith(playerIn: PlayerEntity): Boolean =
            true

    companion object {
        const val REGISTRY_NAME = "duplicator_container"
    }
}
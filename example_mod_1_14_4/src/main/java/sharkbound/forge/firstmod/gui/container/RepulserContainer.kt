package sharkbound.forge.firstmod.gui.container

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import sharkbound.forge.firstmod.gui.ModContainers

class RepulserContainer(var id: Int, val inv: PlayerInventory, val player: PlayerEntity) : Container(ModContainers.REPULSER, id) {
    override fun canInteractWith(playerIn: PlayerEntity): Boolean =
            true

    companion object {
        const val REGISTRY_NAME = "repulser_container"
    }
}
@file:Suppress("MemberVisibilityCanBePrivate")

package com.example.examplemod

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent.Register
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.InterModComms
import net.minecraftforge.fml.InterModComms.IMCMessage
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.*
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager
import sharkbound.commonutils.extensions.ifNotNull
import java.util.stream.Collectors
import javax.swing.JOptionPane
import kotlin.streams.toList

// The value here should match an entry in the META-INF/mods.toml file
@Mod("examplemod")
class ExampleMod {
    fun setup(event: FMLCommonSetupEvent) {
        // some preinit code
        logger.info("HELLO FROM PREINIT")
        logger.info("DIRT BLOCK >> {}", Blocks.DIAMOND_BLOCK.registryName)
    }

    init {
        modEventBus.addListener<FMLCommonSetupEvent> { setup(it) }
        forgeEventBus.register(this)
    }
}
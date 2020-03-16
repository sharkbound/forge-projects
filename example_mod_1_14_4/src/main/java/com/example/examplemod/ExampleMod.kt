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

//    fun doClientStuff(event: FMLClientSetupEvent) {
//        // do something that can only be done on the client
//        logger.info("Got game settings {}", event.minecraftSupplier.get().gameSettings)
//    }

//    fun enqueueIMC(event: InterModEnqueueEvent) {
//        // some example code to dispatch IMC to another mod
//        InterModComms.sendTo("examplemod", "helloworld") {
//            logger.info("Hello world from the MDK")
//            "Hello world"
//        }
//    }

//    fun processIMC(event: InterModProcessEvent) {
//        // some example code to receive and process InterModComms from other mods
//        logger.info("Got IMC {}", event.imcStream.map { m: IMCMessage -> m.getMessageSupplier<Any>().get() }.toList())
//    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
//    @SubscribeEvent
//    fun onServerStarting(event: FMLServerStartingEvent?) {
//        // do something when the server starts
//        logger.info("HELLO from server starting")
//    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
//    @SubscribeEvent
//    fun onBlocksRegistry(blockRegistryEvent: Register<Block?>?) {
//        // register a new block here
//        logger.info("HELLO from Register Block")
//    }

    init {
        // Register the setup method for modloading
        modEventBus.addListener<FMLCommonSetupEvent> { setup(it) }
//        // Register the enqueueIMC method for modloading
//        modEventBus.addListener<InterModEnqueueEvent> { enqueueIMC(it) }
//        // Register the processIMC method for modloading
//        modEventBus.addListener<InterModProcessEvent> { processIMC(it) }
//        // Register the doClientStuff method for modloading
//        modEventBus.addListener<FMLClientSetupEvent> { doClientStuff(it) }

        // Register ourselves for server and other game events we are interested in
        forgeEventBus.register(this)
    }
}
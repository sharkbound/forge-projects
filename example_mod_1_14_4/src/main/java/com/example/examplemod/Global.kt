package com.example.examplemod

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager

val logger get() = LogManager.getLogger()!!
val modLoadingContext get() = FMLJavaModLoadingContext.get()
val modEventBus get() = modLoadingContext.modEventBus
val forgeEventBus get() = MinecraftForge.EVENT_BUS
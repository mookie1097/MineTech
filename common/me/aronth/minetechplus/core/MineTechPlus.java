package me.aronth.minetechplus.core;

import java.util.logging.Logger;

import me.aronth.minetechplus.ideas.IdeaPopper;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version=Reference.VERSION)
public class MineTechPlus {
	
	// A logger too log the mod.. dont know why i need it right now
	public Logger log = FMLLog.getLogger();//Logger.getLogger("MineTech+");
	
	// Mod instance as requested by forge
	@Instance("minetech")
	public static MineTechPlus instance;
	
	// Configuration Handler that handles the config file
	public ConfigHandler config;
	
	// Item and Block Handlers, just for simple management and cleaner code
	public BlockHandler blocks;
	public ItemHandler items;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent e){
		config = new ConfigHandler(e.getSuggestedConfigurationFile());
	}
	
	@Init
	public void initMod(FMLInitializationEvent e){
		items = new ItemHandler(config);
		blocks = new BlockHandler(config);
		
		
		GameRegistry.registerCraftingHandler(new IdeaPopper());
	}
	
}
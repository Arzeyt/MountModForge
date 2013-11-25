package arzeyt.mountMod.common;

import java.util.logging.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid="MountMod",name="Mount Mod",version="0.2")

@NetworkMod(clientSideRequired=true, serverSideRequired=false, 
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"MountMod"}, packetHandler = 
MountModClientPacketHandler.class), 
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"MountMod"}, packetHandler = 
MountModServerPacketHandler.class))
public class MountMod {

	//MOD BASICS

	@Instance("MountMod") //The instance, this is very important later on
	public static MountMod instance = new MountMod();

	@SidedProxy(clientSide = "arzeyt.mountMod.common.MountModClientProxy", 
			serverSide = "arzeyt.mountMod.common.MountModCommonProxy") //Tells Forge the location of your proxies
	
	public static MountModCommonProxy proxy;

	public static Logger logger;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent e){
		logger = e.getModLog();
		logger.setParent(FMLLog.getLogger());
		
		logger.info("Pre-init success.");

	}

	@EventHandler
	public void InitTutorialMod(FMLInitializationEvent event){ //Your main initialization method

		logger.info("Init success.");
		
		//MULTIPLAYER ABILITY
		NetworkRegistry.instance().registerGuiHandler(this, proxy); //Registers the class that deals with GUI data

		//eventlistener
		MinecraftForge.EVENT_BUS.register(new PlayerListener(this));
	}
}

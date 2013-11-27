package arzeyt.mountMod.common;

import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class PlayerListener {
	MountMod instance;
	EntityPlayer player = null;
	
	PlayerListener(MountMod instance){
		this.instance = instance;
	}
	@SideOnly(Side.SERVER)
	  @ForgeSubscribe
	public void onPlayerInteractEvent(EntityInteractEvent event){
	    if (((event.target instanceof EntityPlayer)) && ((event.entity instanceof EntityPlayer)))
	    {
	      EntityPlayer mounter = event.entityPlayer;
	      EntityPlayer mountee = (EntityPlayer)event.target;
	      EntityPlayer unknownPlayer;
	      
	      boolean mounteeIsMounted;
	      boolean mounterIsMounted;
	      boolean mounterHasUnknownPlayer;
	      boolean unknownPlayerIsMountee;
	      boolean mounterPermitedToMount;
	      
	      this.player=mounter;
	      debugPlayer1("event active");

	      
	      try{
	    	  instance.logger.info(mountee.riddenByEntity.getEntityName());
	    	  mounteeIsMounted = true;
	      }catch(Exception e){
	    	  mounteeIsMounted = false;
	      }
	      
	      debugPlayer1(mounteeIsMounted ? "mounteeIsMounted" : "mountee isn't mounted");
	      
	      try{
	    	  instance.logger.info(mounter.riddenByEntity.getEntityName());
	    	  mounterIsMounted = true;
	      }catch(Exception e){
	    	  mounterIsMounted = false;
	      }
	      
	      debugPlayer1(mounterIsMounted ? "mounter is mounted" : "mounter is not mounted");
	      
	      if(mounterIsMounted && mounter.riddenByEntity instanceof EntityPlayer){
	    	  mounterHasUnknownPlayer = true;
	    	  unknownPlayer = (EntityPlayer) mounter.riddenByEntity;
	      }else{
	    	  mounterHasUnknownPlayer = false;
	    	  unknownPlayer = null;
	      }
	      
	      debugPlayer1(mounterHasUnknownPlayer ? "mounter has unkonwn player" : "mounter doesn't have unknown player");
	      
	      if(mounterHasUnknownPlayer){
	    	  if(unknownPlayer.getDisplayName().equalsIgnoreCase(mountee.getDisplayName())){
	    		  //mounter tried to mount someone who is already riding him. Server crash avoided! 
	    		  unknownPlayerIsMountee = true;
	    	  }else{
	    		  unknownPlayerIsMountee = false;
	    	  }
	      }else{
	    	  unknownPlayerIsMountee = false;
	      }
	      
	      debugPlayer1(unknownPlayerIsMountee ? "unknown player is mountee" : "uknown player is not mountee");
	      
	      if(unknownPlayerIsMountee){
	    	  mounterPermitedToMount = false;
	      }else if(mounteeIsMounted){
	    	  mounterPermitedToMount = false;
	      }else{
	    	  mounterPermitedToMount = true;
	      }
	    	  
	      debugPlayer1(mounterPermitedToMount ? "permited to mount":"not permitted to mount");
	      
	      if(mounterPermitedToMount){
	    	  mounter.mountEntity(mountee);
	    	  debugPlayer1("mounted player 1");
	      }
	      
	    }
	  }
	  
	public void debugPlayer1(String str){
		//player.sendChatToPlayer(ChatMessageComponent.createFromText(str));
		return;
	}
}

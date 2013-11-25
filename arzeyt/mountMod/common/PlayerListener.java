package arzeyt.mountMod.common;

import java.util.logging.Level;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class PlayerListener {
	MountMod instance;
	
	PlayerListener(MountMod instance){
		this.instance = instance;
	}
	  @ForgeSubscribe
	  public void onPlayerInteractEvent(EntityInteractEvent event)
	  {
	    if (((event.target instanceof EntityPlayer)) && ((event.entity instanceof EntityPlayer)))
	    {
	      EntityPlayer player1 = event.entityPlayer;
	      EntityPlayer player2 = (EntityPlayer)event.target;
	      
	      try{
	    	  instance.logger.info(player2.riddenByEntity.getEntityName());
	      }catch(Exception e){
	    	  player1.mountEntity(player2);
	      }
	    }
	  }
	
}

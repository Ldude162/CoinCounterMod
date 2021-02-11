package me.coincounter;

import java.lang.annotation.ElementType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = CoinCounter.MODID, version = CoinCounter.VERSION)
public class CoinCounter
{
    public static final String MODID = "coincounter";
    public static final String VERSION = "1.2";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    int totalCoins = 0;
    String mode = "";
    
    @SubscribeEvent
    public void xd(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        //message is the message which the client receives.
        if(message.startsWith("+") && message.contains("coins")) {
            //Checks if the coin message you got isn't the tip message 
            String[] splittedMessage = message.split("coins");
            message = splittedMessage[0].replace("+", "");
            message = message.replace(" ", "");
            int coins = Integer.parseInt(message);
            totalCoins = totalCoins + coins;
        }
    }
    
    @SubscribeEvent
    public void lol(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        //message is the message which the client receives.
        if(message.contains("Protect your bed")) {
            mode = "BedWars";
            totalCoins = 0;
        }
        
        if(message.contains("Gather resources and")) {
        	mode = "SkyWars";
        	totalCoins = 0;
        } 
        
        if(message.contains("Survive and collect")) {
        	
        	mode = "UHC";
        	totalCoins = 0;
        	
        }
        
    }
    
    
    
    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        if (event.isCancelable()) {
            return;
        }
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
        fRender.drawString(EnumChatFormatting.GREEN + mode + " Coins: " + EnumChatFormatting.GOLD + totalCoins, 5, 5, 0);
    }
}

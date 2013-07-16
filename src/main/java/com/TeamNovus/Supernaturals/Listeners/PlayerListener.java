package com.TeamNovus.Supernaturals.Listeners;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Custom.Enchantment.CustomEnchantment;
import com.TeamNovus.Supernaturals.Events.PlayerManaChangeEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class PlayerListener implements Listener {
	
//	@EventHandler
//    public void onPlayerPotionDrink(PlayerCon event) {
//        Player player = event.getPlayer();
//        
//        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
        	// Pseudo:
        	/*
        	 * final boolean switched = false;;
        	 * 
        	 * if(itemInHand().isManaPotion()) {
        	 *  Bukkit.getScheduler.runTaskLater(Supernaturals.getPlugin, new Runnable() {
        	 *          	 *  	public void run() {
        	 *  		if(itemInHand().isEmptyManaPotion()) {
        	 *  			player.restoreMana(itemInHand().getManaAmount());
        	 *  		} else
        	 *  	}
        	 *  }, 35 (33));
        	 * 
        	 * 
        	 * 
        	 */
        	
//        }
//    }
	
	@EventHandler
	public void onPlayerCraftItem(CraftItemEvent event) {
		for(Entry<Enchantment, Integer> entry : event.getRecipe().getResult().getEnchantments().entrySet()) {
			System.out.println(entry.getKey() instanceof CustomEnchantment);
		}
	}
	
	@EventHandler
	public void onPlayerManaChange(PlayerManaChangeEvent event) {	
		SNPlayer player = SNPlayers.i.get(event.getPlayer());
		
		if(player.getMana() < player.getMaxMana()) {
			if(player.getMana() + event.getAmount() > player.getMaxMana()) {
				event.setAmount(player.getMaxMana() - player.getMana());
			}
			
			if(player.isVerbose()) {
				player.sendMessage(ChatColor.GREEN + "+ " + event.getAmount() + " mana!");
			}			
		} else {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerLogin(final PlayerLoginEvent event) {
		SNPlayers.i.get(event.getPlayer());
		
		Bukkit.getServer().getScheduler().runTaskAsynchronously(Supernaturals.getPlugin(), new Runnable() {
			
			public void run() {
				SNPlayer player = SNPlayers.i.get(event.getPlayer());
				
				if(player.isOnline()) {
					player.syncFields(false);
					player.updateGUI();
				}
			}
		});
	}
	
	@EventHandler
	public void onPlayerChangedWorld(final PlayerChangedWorldEvent event) {
		SNPlayers.i.get(event.getPlayer());
		
		Bukkit.getServer().getScheduler().runTaskAsynchronously(Supernaturals.getPlugin(), new Runnable() {
			
			public void run() {
				SNPlayer player = SNPlayers.i.get(event.getPlayer());
				
				if(player.isOnline()) {
					player.syncFields(false);
					player.updateGUI();
				}
			}
		});
	}

}

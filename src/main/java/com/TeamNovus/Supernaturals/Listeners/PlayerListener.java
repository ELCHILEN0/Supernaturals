package com.TeamNovus.Supernaturals.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Events.PlayerManaChangeEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class PlayerListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		SNPlayer player = SNPlayers.i.get(event.getPlayer());

		event.setFormat(event.getFormat().replace("{{ CLASS_COLOR }}", player.getPlayerClass().getColor() + ""));
		event.setFormat(event.getFormat().replace("{{ CLASS }}", player.getPlayerClass().getName()));
		event.setFormat(event.getFormat().replace("{{ LEVEL }}", player.getLevel() + ""));
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

}

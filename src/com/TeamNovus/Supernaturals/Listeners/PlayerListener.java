package com.TeamNovus.Supernaturals.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Events.PlayerChangeClassEvent;
import com.TeamNovus.Supernaturals.Models.ItemBag;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerLogin(final PlayerLoginEvent event) {
		SNPlayers.i.get(event.getPlayer());
		
		Bukkit.getServer().getScheduler().runTaskAsynchronously(Supernaturals.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				SNPlayer player = SNPlayers.i.get(event.getPlayer());
				
				if(player.isOnline()) {
					player.syncFields();
				}
			}
		});
	}
	
	@EventHandler
	public void onPlayerChangeClass(PlayerChangeClassEvent event) {
		SNPlayer player = SNPlayers.i.get(event.getPlayer());
		
		if(player.isOnline()) {
			player.syncFields();
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {	
		// Bind all Spells to the BLAZE_ROD
		if (!(event.getPlayer().getItemInHand().getData().getItemType().equals(Material.BLAZE_ROD)))
			return;

		SNPlayer player = SNPlayers.i.get(event.getPlayer());
		
		// Bind/Switch:
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) ||
				event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			player.setNextBinding();

			if (player.getSelectedPower() != null) {
				player.sendMessage(ChatColor.GREEN + "Wand bound to " + ChatColor.YELLOW + player.getSelectedPower().getName() + ChatColor.GREEN + "!");
			}
		}

		// Cast:
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)) {
			if (player.getSelectedPower() != null) {
				Power power = player.getSelectedPower();
				
				if(player.getRemainingCooldown(power) >= 0) {
					player.sendMessage(ChatColor.RED + "You must wait " + ChatColor.YELLOW + player.getRemainingCooldown(power) / 1000 + ChatColor.RED + " seconds to cast this spell!");
				} else if (power.getRequired().has(player.getPlayer())) {
					if (power.cast(event.getPlayer())) {
						power.getConsume().consume(player.getPlayer());
						player.setCooldown(power, System.currentTimeMillis());
					}
				} else {
					player.sendMessage(ChatColor.BLUE + "Requires:");
					if (power.getRequired().getMoneyCost() != 0)
						player.sendMessage(ChatColor.BLUE + "   Money: " + ChatColor.YELLOW + power.getRequired().getMoneyCost());
					if (power.getRequired().getExpCost() != 0)
						player.sendMessage(ChatColor.BLUE + "   Experience: " + ChatColor.YELLOW + power.getRequired().getExpCost());
					if (power.getRequired().getHealthCost() != 0)
						player.sendMessage(ChatColor.BLUE + "   Health: " + ChatColor.YELLOW + power.getRequired().getHealthCost());
					if (power.getRequired().getHungerCost() != 0)
						player.sendMessage(ChatColor.BLUE + "   Hunger: " + ChatColor.YELLOW + power.getRequired().getHungerCost());
					if (power.getRequired().getPowerCost() != 0)
						player.sendMessage(ChatColor.BLUE + "   Power: " + ChatColor.YELLOW + power.getRequired().getPowerCost());
					if (power.getRequired().getItemBagCost() != new ItemBag())
						player.sendMessage(ChatColor.BLUE + "   Items: " + ChatColor.YELLOW + power.getRequired().getItemBagCost().toString());
				}
			} else {
				player.sendMessage(ChatColor.RED + "Your wand is not bound to a power!");
			}
		}
	}

}

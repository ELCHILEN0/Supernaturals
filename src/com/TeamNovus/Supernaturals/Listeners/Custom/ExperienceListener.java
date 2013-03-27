package com.TeamNovus.Supernaturals.Listeners.Custom;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Events.PlayerLevelUpEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class ExperienceListener implements Listener {
	
	@EventHandler
	public void onPlayerLevelUp(PlayerLevelUpEvent event) {
		SNPlayer player = SNPlayers.i.get(event.getPlayer());
		
		event.getPlayer().sendMessage(ChatColor.GREEN + "You are now level " + ChatColor.YELLOW + player.getLevel() + ChatColor.GREEN + "!");
		Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now a level " + ChatColor.YELLOW + player.getLevel() + " " + player.getPlayerClass().getColor() + player.getPlayerClass().getName() + ChatColor.GREEN + "!");
	}
	
	public void gainExp(SNPlayer player, Integer exp) {
		if(Supernaturals.getPlugin().getConfig().getStringList("settings.disabled-worlds").contains(player.getPlayer().getWorld().getName().toLowerCase())) {
			return;
		}
		
		if(player.getLevel() < player.getPlayerClass().getMaxLevel()) {
			player.setExperience(player.getExperience() + exp);
			
			player.sendMessage(ChatColor.GOLD + "Experience: " + ChatColor.RESET + player.getExperience() + "/" + player.getExperienceFor(player.getLevel() + 1));
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		SNPlayer player = SNPlayers.i.get(event.getPlayer());

		HashMap<Material, Integer> values = new HashMap<Material, Integer>();
		values.put(Material.LOG, 1);
		values.put(Material.COAL_ORE, 1);
		values.put(Material.IRON_ORE, 1);
		values.put(Material.GOLD_ORE, 3);
		values.put(Material.REDSTONE_ORE, 1);
		values.put(Material.LAPIS_ORE, 3);
		values.put(Material.DIAMOND_ORE, 7);
		values.put(Material.OBSIDIAN, 7);
		
		if(values.keySet().contains(event.getBlock().getType())) {
			gainExp(player, values.get(event.getBlock().getType()));
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if(event.getEntity().getKiller() == null) return;
		
		SNPlayer killer = SNPlayers.i.get(event.getEntity().getKiller());
		
		if(event.getEntity() instanceof Player) {
			gainExp(killer, 50);
		} else if(event.getEntity() instanceof Monster) {
			gainExp(killer, 5);
		}
	}

}

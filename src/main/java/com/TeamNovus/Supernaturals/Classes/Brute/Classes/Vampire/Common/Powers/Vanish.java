package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Powers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Vanish extends Power {
	private Integer range;
	private Integer duration;
	
	public Vanish(String name, String desc, Integer cooldown, Integer range, Integer duration, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.range = range;
		this.duration = duration;
	}

	@Override
	public Boolean cast(final SNPlayer player) {
		for(final Entity entity : player.getPlayer().getNearbyEntities(range, range, range)) {
			if(entity instanceof Player) {
				((Player) entity).hidePlayer(player.getPlayer());
				((Player) entity).sendMessage(ChatColor.ITALIC + "" + ChatColor.YELLOW + player.getName() + ChatColor.RED + " has vanished.");
				
				Bukkit.getScheduler().runTaskLater(Supernaturals.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						((Player) entity).showPlayer(player.getPlayer());
					}
				}, duration * 20);
			}
		}
		
		player.sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "You have been vanished!");
		return true;
	}

}

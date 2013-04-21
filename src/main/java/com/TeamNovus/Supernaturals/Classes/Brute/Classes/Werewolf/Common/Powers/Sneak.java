package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf.Common.Powers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Sneak extends Power {
	private Integer duration;

	public Sneak(String name, String desc, Integer cooldown, Integer duration, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.duration = duration;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {
		final Player p = player.getPlayer();
		
		p.setSneaking(true);
		p.sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "You have begun sneaking!");
		
		Bukkit.getScheduler().runTaskLater(Supernaturals.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				p.setSneaking(false);
				p.sendMessage(ChatColor.ITALIC + "" + ChatColor.RED + "You are no longer sneaking!");
			}
		}, 20 * duration);
		
		return true;
	}

}

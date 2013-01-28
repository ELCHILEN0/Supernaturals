package com.TeamNovus.Supernaturals.Classes.Rogue.RogueCommon.Powers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Sneak extends Power {

	public Sneak(String name, String desc, Integer amplifier, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, amplifier, cooldown, required, consume);
	}

	@Override
	public Boolean cast(final SNPlayer player) {
		player.getPlayer().setSneaking(true);
		player.sendMessage(ChatColor.DARK_GRAY + "You have began sneaking!");
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Supernaturals.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				player.getPlayer().setSneaking(true);
				player.sendMessage(ChatColor.DARK_GRAY + "You are no longer sneaking!");
			}
		}, 10 * getAmplifier() * 20);
		
		return true;
	}
}

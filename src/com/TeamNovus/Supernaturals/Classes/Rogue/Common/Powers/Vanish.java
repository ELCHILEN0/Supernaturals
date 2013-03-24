package com.TeamNovus.Supernaturals.Classes.Rogue.Common.Powers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Vanish extends Power {
	private Integer duration;

	public Vanish(String name, String desc, Integer duration, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.duration = duration;
	}

	@Override
	public Boolean cast(final SNPlayer player) {
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.getPlayer().hidePlayer(player.getPlayer());
			players.add(p);
		}
		player.sendMessage(ChatColor.DARK_GRAY + "You have vanished!");
		
		final ArrayList<Player> toRemove = players;
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Supernaturals.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				for(Player p : toRemove) {
					p.showPlayer(player.getPlayer());
				}
				player.sendMessage(ChatColor.DARK_GRAY + "You are no longer vanished!");
			}
		}, duration * 20);
		
		return true;
	}

}

package com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Assassin.Powers;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Blink extends Power {
	private Integer maxDistance;
	
	public Blink(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		maxDistance = 255;
	}

	public Blink setMaxDistance(Integer maxDistance) {
		this.maxDistance = maxDistance;
		
		return this;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {
		Block target = player.getPlayer().getTargetBlock(null, maxDistance);
		
		if(target != null) {
			player.getPlayer().teleport(target.getLocation(), TeleportCause.PLUGIN);
			return true;
		}

		player.sendMessage(ChatColor.RED + "No block was found within your range of " + ChatColor.YELLOW + maxDistance + ChatColor.RED + "blocks!");		
		return false;
	}



}

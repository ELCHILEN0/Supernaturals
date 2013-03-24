package com.TeamNovus.Supernaturals.Classes.Rogue.Common.Powers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Leap extends Power {
	
	public Leap(String name, String desc, Integer amplifier, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, amplifier, cooldown, required, consume);
	}

	@Override
	public Boolean cast(SNPlayer player) {
		for(Block b : player.getPlayer().getLineOfSight(null, 15)) {
			if(!(b.getType().equals(Material.AIR))) {
				player.getPlayer().teleport(b.getLocation(), TeleportCause.PLUGIN);
				return true;
			}
		}
		
		player.sendMessage(ChatColor.RED + "You cannot leap here!");		
		return false;
	}

}

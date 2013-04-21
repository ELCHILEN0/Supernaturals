package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf.Common.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Pounce extends Power {
	private Integer range;

	public Pounce(String name, String desc, Integer cooldown, Integer range, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.range = range;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {		
		LivingEntity target = player.getTargetEntity(range);
		if(target == null) {
			player.sendMessage(ChatColor.RED + "No entity was found within your range!");
			return false;
		}

		target.damage(3, player.getPlayer());
		player.getPlayer().teleport(target, TeleportCause.PLUGIN);
		
		return true;
	}
}

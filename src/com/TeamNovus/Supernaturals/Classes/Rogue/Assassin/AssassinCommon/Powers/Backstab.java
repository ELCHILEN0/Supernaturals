package com.TeamNovus.Supernaturals.Classes.Rogue.Assassin.AssassinCommon.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Backstab extends Power {

	public Backstab(String name, String desc, Integer amplifier, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, amplifier, cooldown, required, consume);
	}

	@Override
	public Boolean cast(SNPlayer player) {
		LivingEntity target = player.getTargetEntity(5);
		
		if(target == null) {
			player.sendMessage(ChatColor.RED + "No enemy was found in your range!");
			return false;
		}
		
		target.damage(10, player.getPlayer());
		player.getPlayer().teleport(target);	
		return true;
	}
}

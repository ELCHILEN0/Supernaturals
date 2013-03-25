package com.TeamNovus.Supernaturals.Classes.Warrior.Common.Abilities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class DoubleStrike extends Ability {
	private Integer chance;
	
	public DoubleStrike(String name, String desc, Integer chance, Integer amplifier) {
		super(name, desc, amplifier);
		
		this.chance = chance;
	}
	
	public void onEntityDamageEntity(EntityDamageEntityEvent event) {
		if(new Random().nextInt(101) <= chance) {
			event.setDamage(event.getDamage() * 2);
			
			if(event.getEntity() instanceof Player) {
				SNPlayer damager = SNPlayers.i.get((Player) event.getEntity());
				
				damager.sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "Double strike!");
			}
		}
	}
}

package com.TeamNovus.Supernaturals.Classes.Brute.Common.Abilities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class Spikes extends Ability {
	private Integer chance;
	private Integer damage;

	public Spikes(String name, String desc, Integer amplifier, Integer chance, Integer damage) {
		super(name, desc, amplifier);

		this.chance = chance;
		this.damage = damage;
	}

	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(new Random().nextInt(101) <= chance) {			
			if(event.getDamager() instanceof Damageable) {
				((Damageable) event.getDamager()).damage(damage);
				
				
				if(event.getEntity() instanceof Player) {
					((Player) event.getEntity()).sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "Spiked Attacker!");
				}
	
				if(event.getDamager() instanceof Player) {
					((Player) event.getDamager()).sendMessage(ChatColor.ITALIC + "" + ChatColor.RED + "Spiked!");
				}
			}
		}
	}

}

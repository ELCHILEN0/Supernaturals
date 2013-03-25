package com.TeamNovus.Supernaturals.Classes.Brute.Common.Abilities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class Block extends Ability {
	private Integer chance;

	public Block(String name, String desc, Integer amplifier, Integer chance) {
		super(name, desc, amplifier);

		this.chance = chance;
	}

	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(new Random().nextInt(101) <= chance) {			
			event.setCancelled(true);
			
			if(event.getEntity() instanceof Player) {
				((Player) event.getEntity()).sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "Blocked!");
			}

			if(event.getDamager() instanceof Player) {
				((Player) event.getDamager()).sendMessage(ChatColor.ITALIC + "" + ChatColor.RED + "Attack Blocked!");
			}
		}
	}

}

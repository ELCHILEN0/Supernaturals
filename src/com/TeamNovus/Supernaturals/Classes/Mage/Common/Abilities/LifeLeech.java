package com.TeamNovus.Supernaturals.Classes.Mage.Common.Abilities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class LifeLeech extends Ability {
	private Integer chance;
	private Integer amount;

	public LifeLeech(String name, String desc, Integer amplifier, Integer amount, Integer chance) {
		super(name, desc, amplifier);
		
		this.chance = chance;
		this.amount = amount;
	}
	
	public void onEntityDamageEntity(EntityDamageEntityEvent event) {
		if(new Random().nextInt(101) <= chance) {			
			if(event.getEntity() instanceof Player && event.getDamaged() instanceof Player) {
				SNPlayer damager = SNPlayers.i.get((Player) event.getEntity());
				
				Integer actual = damager.getHealth() + amount > damager.getMaxHealth() ? damager.getMaxHealth() - amount : amount;
				damager.setHealth(damager.getHealth() + amount);
				
				damager.sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "Drained " + ChatColor.YELLOW + actual + ChatColor.GREEN + " health!");
			}
		}
	}
}

package com.TeamNovus.Supernaturals.Classes.Mage.Common.Abilities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class ManaLeech extends Ability {
	private Integer chance;
	private Integer amount;

	public ManaLeech(String name, String desc, Integer amplifier, Integer amount, Integer chance) {
		super(name, desc, amplifier);
		
		this.chance = chance;
		this.amount = amount;
	}
	
	public void onEntityDamageEntity(EntityDamageEntityEvent event) {
		if(new Random().nextInt(101) <= chance) {			
			if(event.getEntity() instanceof Player && event.getDamaged() instanceof Player) {
				SNPlayer damager = SNPlayers.i.get((Player) event.getEntity());
				SNPlayer damaged = SNPlayers.i.get((Player) event.getDamaged());
				
				Integer actual = damaged.getMana() - amount > 0 ? amount : damaged.getMana();
				actual = damager.getMana() + actual > damager.getMaxMana() ? damager.getMaxMana() - damager.getMana() : actual;
				
				damager.setMana(damager.getMana() + actual);
				damaged.setMana(damaged.getMana() - actual);
				
				damager.sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "Drained " + ChatColor.YELLOW + actual + ChatColor.GREEN + " mana!");
				damager.sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "Lost " + ChatColor.YELLOW + actual + ChatColor.GREEN + " mana!");
			}
		}
	}
}

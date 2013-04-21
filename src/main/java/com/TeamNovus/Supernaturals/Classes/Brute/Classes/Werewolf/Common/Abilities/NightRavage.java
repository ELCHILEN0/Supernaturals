package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf.Common.Abilities;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class NightRavage extends Ability {

	public NightRavage(String name, String desc, Integer amplifier) {
		super(name, desc, amplifier);
	}
	
	public void onEntityDamageEntity(EntityDamageEntityEvent event) {
		if(event.getEntity().getWorld().getTime() >= 12000) {
			event.setDamage(event.getDamage() + 2);
		}
	}
	
	public void onEntityDamageByEntity(EntityDamageEvent event) {
		if(event.getEntity().getWorld().getTime() < 12000) {
			event.setDamage(event.getDamage() + 2);
		}
	}
	
	public void onEntityTick(EntityEffectTickEvent event) {
		if(event.getEntity() instanceof Player) {
			if(event.getEntity().getWorld().getTime() == 12000)
				((Player) event.getEntity()).sendMessage(ChatColor.DARK_GRAY + "You shiftshape into beast form increasing damage!");
			
			if(event.getEntity().getWorld().getTime() == 0)
				((Player) event.getEntity()).sendMessage(ChatColor.DARK_GRAY + "You return to a weaker human form.");
		}
	}

}

package com.TeamNovus.Supernaturals.Classes.Warrior.Common.Powers;

import org.bukkit.ChatColor;
import org.bukkit.event.entity.EntityDamageEvent;

import com.TeamNovus.Supernaturals.Entity.Effect;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Shell extends Power {
	public Integer duration;
	
	public Shell(String name, String desc, Integer duration, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
	}

	public Boolean cast(SNPlayer player) {
		player.getEntity().addEffect(new ShellEffect(duration));
		
		player.sendMessage(ChatColor.GREEN + "A shell encloses you reducing damage!");
		
		return true;
	}
	
	public class ShellEffect extends Effect {
		
		public ShellEffect(Integer duration) {
			setDuration(duration);
		}
		
		public void onEntityDamage(EntityDamageEvent event) {
			event.setDamage(event.getDamage() / 2);
		}
		
	}

}

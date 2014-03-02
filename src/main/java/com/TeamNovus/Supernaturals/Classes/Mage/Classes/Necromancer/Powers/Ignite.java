package com.TeamNovus.Supernaturals.Classes.Mage.Classes.Necromancer.Powers;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Ignite extends Power {
	private int range;
	private int duration;

	public Ignite(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
	}
	
	public Ignite(String name, String desc, Integer cooldown, Reagent reagent) {
		this(name, desc, cooldown, reagent, reagent);
	}

	@Override
	public Boolean cast(SNPlayer player) {
		LivingEntity target = player.getTargetEntity(range);

		target.setFireTicks(duration);
		
		player.sendMessage("You have lit your target on fire!");
		if(target instanceof Player) {
			((Player) target).sendMessage(player.getName() + " has lit you on fire!");
		}
		
		return true;
	}
	
	public Ignite setRange(int range) {
		this.range = range;

		return this;
	}
	
	public Ignite setDuration(int duration) {
		this.duration = duration;
		
		return this;
	}

}

package com.TeamNovus.Supernaturals.Classes.Barbarian.Powers;

import org.bukkit.ChatColor;
import org.bukkit.Color;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;
import com.TeamNovus.Supernaturals.Util.ParticleEffectUtils;

public class Quicken extends Power {
	private int duration = 20 * 15;
	private int amplifier = 2;

	public Quicken(String name, String desc, Integer cooldown, Reagent reagent) {
		super(name, desc, cooldown, reagent);
	}	

	public Quicken(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
	}

	@Override
	public Boolean cast(SNPlayer player) {		
		player.getEntity().addEffect(new Effect(EffectType.SPEED, duration, amplifier));
		
		ParticleEffectUtils.fireworkParticleShower(player.getPlayer().getLocation(), Color.FUCHSIA);
		player.sendMessage(ChatColor.GREEN + "You suddenly feel lighter and can run faster!");
		
		return true;
	}

	public Quicken setDuration(int duration) {
		this.duration = duration;
		
		return this;
	}
	
	public Quicken setAmplifier(int amplifier) {
		this.amplifier = amplifier;
		
		return this;
	}
	
}

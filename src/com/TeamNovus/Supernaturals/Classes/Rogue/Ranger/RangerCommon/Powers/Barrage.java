package com.TeamNovus.Supernaturals.Classes.Rogue.Ranger.RangerCommon.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.util.Vector;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Barrage extends Power {

	public Barrage(String name, String desc, Integer amplifier, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, amplifier, cooldown, required, consume);
	}

	@Override
	public Boolean cast(SNPlayer player) {
		double diff = 0.2617993877991494D;
		for (double a = 0.0D; a < 6.283185307179586D; a += diff) {
			Vector vel = new Vector(Math.cos(a), 0.0D, Math.sin(a));
			Arrow arrow = player.getPlayer().getWorld().spawnArrow(player.getPlayer().getLocation(), vel, 5F, 2F);
			arrow.setShooter(player.getPlayer());
		}
		
		player.sendMessage(ChatColor.DARK_GRAY + "A barrage of arrows falls from the skies!");
		
		return true;
	}

}

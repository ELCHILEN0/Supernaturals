package com.TeamNovus.Supernaturals.Classes.Barbarian.Powers;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;
import com.TeamNovus.Supernaturals.Util.ParticleEffectUtils;

public class Mar extends Power {
	private int range = 15;
	private int damage = 20;

	public Mar(String name, String desc, Integer cooldown, Reagent reagent) {
		super(name, desc, cooldown, reagent);
	}	

	public Mar(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
	}

	@Override
	public Boolean cast(SNPlayer player) {
		LivingEntity target = player.getTargetEntity(range);
		
		if(target == null || !(target instanceof Player)) {
			player.sendMessage(ChatColor.RED + "No player was found within " + ChatColor.YELLOW + range + ChatColor.RED + " blocks!");
			return false;
		}
		
		if(((Player) target).getInventory().getArmorContents().length == 0) {
			player.sendMessage(ChatColor.RED + "This player is not wearing armor!");
			return false;
		}
		
		for (ItemStack armor : ((Player) target).getInventory().getArmorContents()) {
			armor.setDurability((short) (armor.getDurability() - damage > 0 ? armor.getDurability() - damage : 0));
		}
		
		player.sendMessage(ChatColor.GREEN + "You have damaged all of " + ((Player) target) + "'s armor by " + ChatColor.YELLOW + damage + ChatColor.GREEN + "!");
		((Player) target).sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "has damaged your armor with a spell!");
		
		ParticleEffectUtils.fireworkParticleShower(player.getPlayer().getLocation(), Color.FUCHSIA);
		ParticleEffectUtils.fireworkParticleShower(target.getLocation(), Color.RED);
		return true;
	}

	public Mar setRange(int range) {
		this.range = range;
		
		return this;
	}
	
	public Mar setDamage(int damage) {
		this.damage = damage;
		
		return this;
	}
	
}

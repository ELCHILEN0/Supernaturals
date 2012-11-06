package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;
import com.TeamNovus.SupernaturalRaces.Util.SpellUtil;

public class Scent implements Spell {

	@Override
	public String name() {
		return "Scent";
	}

	@Override
	public String info() {
		return "Make your compass point towards a player for 5 minutes!";
	}

	@Override
	public Material binding() {
		return Material.COMPASS;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 500, new ItemBag(new ItemStack(Material.COMPASS, 1)));
	}

	@Override
	public Reagent consume() {
		return new Reagent(0.0, 0, 0, 0, 500, new ItemBag());
	}

	@Override
	public Boolean execute(Player sender) {
		LivingEntity targetEntity = SpellUtil.getTargetedEntity(sender, 30);
		
		if(targetEntity == null) {
			sender.sendMessage(ChatColor.RED + "You must be looking at an entity!");
			return false;
		}
		
		final Player player = sender;
		final LivingEntity entity = targetEntity;
		
		SupernaturalRaces.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(
				SupernaturalRaces.getPlugin(),
				new Runnable() {
					@Override
					public void run() {
						player.setCompassTarget(entity.getLocation());						
					}
				}, 1L, 1L);
		
		SupernaturalRaces.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(
				SupernaturalRaces.getPlugin(),
				new Runnable() {
					@Override
					public void run() {
						player.setCompassTarget(player.getWorld().getSpawnLocation());	
						player.sendMessage(ChatColor.GOLD + "You have lost the scent!");
					}
				}, 20L * 60 * 5);
		
		sender.setCompassTarget(targetEntity.getLocation());
		sender.sendMessage(ChatColor.GOLD + "Your compass points towards your enemy!");
		return true;
	}

}

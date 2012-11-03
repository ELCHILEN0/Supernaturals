package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Scent implements SNSpell {

	@Override
	public String name() {
		return "Scent";
	}

	@Override
	public String desc() {
		return "Track an enemy for up to 5 minutes!";
	}

	@Override
	public List<Action> actions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(Action.LEFT_CLICK_AIR);
		return actions;
	}

	@Override
	public List<Material> bindings() {
		List<Material> bindings = new ArrayList<Material>();
		bindings.add(Material.COMPASS);
		return bindings;
	}

	@Override
	public Integer power() {
		return 50;
	}

	@Override
	public Boolean consume() {
		return false;
	}

	@Override
	public Boolean execute(Player sender) {
		LivingEntity targetEntity = null;
		for(Block targetBlock : sender.getLineOfSight(null, 50)) {
			Location blockLoc = targetBlock.getLocation();
			double bx = blockLoc.getX();
			double by = blockLoc.getY();
			double bz = blockLoc.getZ();
			List<Entity> e = sender.getNearbyEntities(50, 50, 50);

			for (Entity entity : e) {
				Location loc = entity.getLocation();
				double ex = loc.getX();
				double ey = loc.getY();
				double ez = loc.getZ();
				
				if ((bx-1.5 <= ex && ex <= bx+2) && (bz-1.5 <= ez && ez <= bz+2) && (by-1 <= ey && ey <= by+2.5)) {
					targetEntity = (LivingEntity) entity;
					break;
				}
			}
		}
		
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

package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Howl implements SNSpell {
	
	@Override
	public String name() {
		return "WolfHowl";
	}

	@Override
	public String desc() {
		return "Call mobs to the attack with your nightly howl!";
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
		bindings.add(Material.SULPHUR);
		return bindings;
	}

	@Override
	public Integer power() {
		return 75;
	}

	@Override
	public Boolean consume() {
		return true;
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
                
		for(Entity e : sender.getNearbyEntities(20, 20, 20)) {
			if(e instanceof Monster) {
				Monster entity = (Monster) e;
				entity.setTarget(targetEntity);
			}
		}
		
		sender.sendMessage(ChatColor.GOLD + "The creatures have been summoned to attack your enemies!");
		return true;
	}

}
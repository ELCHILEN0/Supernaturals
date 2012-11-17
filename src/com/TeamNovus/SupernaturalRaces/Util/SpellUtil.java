package com.TeamNovus.SupernaturalRaces.Util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.EntityWolf;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.util.BlockIterator;

public class SpellUtil {

	public static LivingEntity getTargetedEntity(Player player, Integer range) {
		// Get the nearby entities
		List<Entity> near = player.getNearbyEntities(range, range, range);
		List<LivingEntity> entities = new ArrayList<LivingEntity>(); 
		for (Entity e : near) {
			if (e instanceof LivingEntity) {
				entities.add((LivingEntity) e);
			}
		}

		// Find the target
		LivingEntity target = null;		
		BlockIterator iterator = new BlockIterator(player, range);

		Block b;
		Location l;
		int bx, by, bz;
		double ex, ey, ez;
		// Loop through the players line of sight
		while (iterator.hasNext()) {
			b = iterator.next();
			bx = b.getX();
			by = b.getY();
			bz = b.getZ();
			
			if(b.getType() != Material.AIR) {
				break;
			}

			// Check each entity in the range to see if its near the line of sight
			for (LivingEntity e : entities) {
				l = e.getLocation();
				ex = l.getX();
				ey = l.getY();
				ez = l.getZ();

				// Check if the entity is near the line of sight
				if ((bx-.75 <= ex && ex <= bx+1.75) && (bz-.75 <= ez && ez <= bz+1.75) && (by-1 <= ey && ey <= by+2.5)) {
					target = e;
					return target;
				}
			}
		}

		return null;
	}
	
	
    public static Wolf setCollarColor(Wolf wolf, DyeColor color){
        EntityWolf ent = (EntityWolf) ((CraftLivingEntity)wolf).getHandle();
        ent.setCollarColor(color.getData());
        return wolf;
    }
	
}

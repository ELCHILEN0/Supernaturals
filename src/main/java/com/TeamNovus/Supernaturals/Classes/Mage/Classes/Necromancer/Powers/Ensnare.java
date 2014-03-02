package com.TeamNovus.Supernaturals.Classes.Mage.Classes.Necromancer.Powers;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Ensnare extends Power {
	private int range;
	private int radius;
	private int duration;
	
	public Ensnare(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		range = 256;
		radius = 3;
		duration = 30;
	}
	
	public Ensnare(String name, String desc, Integer cooldown, Reagent reagent) {
		this(name, desc, cooldown, reagent, reagent);
	}
	
	public Ensnare setRange(int range) {
		this.range = range;
		
		return this;
	}
	
	public Ensnare setRadius(int radius) {
		this.radius = radius;
		
		return this;
	}
	
	public Ensnare setDuration(int duration) {
		this.duration = duration;
		
		return this;
	}

	@Override
	public Boolean cast(SNPlayer player) {
		LivingEntity target = player.getTargetEntity(range);
		
		if(target != null) {
			final HashMap<Block, Material> changed = new HashMap<Block, Material>();
			
			for (int x = -radius; x <= radius; x++) {
				for (int y = -radius; y <= radius; y++) {
					for (int z = -radius; z <= radius; z++) {
						Block b = target.getLocation().getBlock().getRelative(x, y, z);
						
						if(b.getType() != Material.WEB) {
							if(b.getType() == Material.AIR) {
								changed.put(b, b.getType());
								b.setType(Material.WEB);
							}
						}
					}
				}
			}
			
			Bukkit.getScheduler().runTaskLater(Supernaturals.plugin, new Runnable() {
				
				@Override
				public void run() {
					for (Entry<Block, Material> entry : changed.entrySet()) {
						entry.getKey().setType(entry.getValue());
					}
				}
			}, duration);
			
			
			return true;
		}
		
		
		player.sendMessage(ChatColor.RED + "No player was found within your range of " + ChatColor.YELLOW + range + ChatColor.RED + " blocks!");
		return false;
	}

}

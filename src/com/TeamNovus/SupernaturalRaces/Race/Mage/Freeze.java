package com.TeamNovus.SupernaturalRaces.Race.Mage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;
import com.TeamNovus.SupernaturalRaces.Util.SpellUtil;

public class Freeze implements Spell {

	@Override
	public String name() {
		return "Freeze";
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 150, new ItemBag(new ItemStack(Material.STICK, 1)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Material binding() {
		return Material.STICK;
	}

	@Override
	public Boolean execute(Player sender) {
		LivingEntity entity = SpellUtil.getTargetedEntity(sender, 20);
		
		if(entity == null) {
			sender.sendMessage(ChatColor.RED + "No entity was found!");
			return false;
		}
		
		List<Block> changed = new ArrayList<Block>();
		int radius = 3;
		for (int x = -(radius); x <= radius; x++) {
			for (int y = -(radius); y <= radius; y++) {
				for (int z = -(radius); z <= radius; z++) {
					Block block = entity.getLocation().getBlock().getRelative(x, y, z);
					if(block.getType().equals(Material.AIR)) {
						changed.add(block);
						block.setType(Material.ICE);
					}
				}
			}
		}
		
		sender.sendMessage(ChatColor.GOLD + "Your enemy has been frozen in ice!");
		
		final List<Block> restore = changed;
		SupernaturalRaces.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(
				SupernaturalRaces.getPlugin(), 
				new Runnable() {
					
					@Override
					public void run() {
						for(Block block : restore) {
							block.setType(Material.AIR);
						}
						
					}
				}, 20 * 30);
		
		return true;
	}

}
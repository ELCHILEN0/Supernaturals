package com.TeamNovus.Supernaturals.Classes.Mage.Common.Powers;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Heal extends Power {
	private Integer range;

	public Heal(String name, String desc, Integer range, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.range = range;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {
		List<Entity> entities = player.getPlayer().getNearbyEntities(range, range, range);
		
		entities.add(player.getPlayer());
		
		int healed = 0;
		
		for(Entity e : entities) {
			if(e instanceof Player) {
				healed++;
				
				((Player) e).setHealth(SNPlayers.i.get((Player) e).getMaxHealth());
				
				if(!(e.equals(player.getPlayer()))) {
					((Player) e).sendMessage(ChatColor.GREEN + "You were healed by " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "!");
				}
			}
		}
		
		player.sendMessage(ChatColor.GREEN + "You just healed " + ChatColor.YELLOW + healed + ChatColor.GREEN + " players!");
		
		return true;
	}
	
	

}

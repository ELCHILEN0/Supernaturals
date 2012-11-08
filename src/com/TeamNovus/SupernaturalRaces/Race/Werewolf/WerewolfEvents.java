package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageByEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class WerewolfEvents implements SNEventListener {

	@SNEventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		if(event.getPlayer().getWorld().getTime() > 12000) {
			event.setDamage((int) (event.getDamage() * 1.25));
		}
	}
	
	@SNEventHandler
	public void onPlayerDamageByEntity(PlayerDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player attacker = (Player) event.getEntity();
			if(attacker.getItemInHand().equals(new ItemStack(Material.IRON_SWORD))) {
				event.setDamage(event.getDamage()*2);
			}
		}
	}
}

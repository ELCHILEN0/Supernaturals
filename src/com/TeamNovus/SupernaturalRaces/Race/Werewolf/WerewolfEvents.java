package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageByEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerKillEntityEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Race.Human.HumanRace;
import com.TeamNovus.SupernaturalRaces.Race.Mage.MageRace;
import com.TeamNovus.SupernaturalRaces.Race.Priest.PriestRace;

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
	
	@SNEventHandler
	public void onPlayerKillEntity(PlayerKillEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player killed = (Player) event.getEntity();
			SNPlayer k = SupernaturalRaces.getPlayerManager().getPlayer(killed);
			if(k.getPlayerRace() instanceof MageRace || k.getPlayerRace() instanceof HumanRace || k.getPlayerRace() instanceof PriestRace) {
				if((Math.random() * 100) < 5 && killed.getWorld().getTime() > 12000) {
					k.setRace(new WerewolfRace());
					killed.sendMessage(ChatColor.RED + "Your wild side begins to take over!  You are now a Werewolf!");
					event.getPlayer().sendMessage(ChatColor.GREEN + "Your bite has caused " + ChatColor.YELLOW + killed.getName() + ChatColor.GREEN + "to become a Werewolf!");
					return;
				}
			}
		}
	}
}

package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
import com.TeamNovus.SupernaturalRaces.Race.Werewolf.WerewolfRace;

public class VampireEvents implements SNEventListener {

	@SNEventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			if((Math.random()*100) < 5) {
				Player player = (Player) event.getEntity();
				SNPlayer snp = SupernaturalRaces.getPlayerManager().getPlayer(player);
				snp.setRemainingBleeding(snp.getRemainingBleeding() + 20*15);
				event.getPlayer().sendMessage(ChatColor.GOLD + "You have made your enemy bleed!");
			}
		}
		
		if(event.getPlayer().getWorld().getTime() > 12000) {
			event.setDamage((int) (event.getDamage() * 1.25));
		}
	}
	
	@SNEventHandler
	public void onPlayerDamageByEntity(PlayerDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player attacker = (Player) event.getEntity();
			if(attacker.getItemInHand().equals(new ItemStack(Material.WOOD_SWORD))) {
				event.setDamage(event.getDamage()*2);
			}
		}
	}

	@SNEventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if(player.getWorld().getTime() > 12000) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 3, 0));
		}

		if(player.getWorld().getTime() < 12000 && player.isSprinting()) {
			if((Math.random()*100) < 10) {
				player.setFireTicks(20 * 1);
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
					killed.sendMessage(ChatColor.RED + "Your senses awaken and you thirst for blood!  You are now a Vampire!");
					event.getPlayer().sendMessage(ChatColor.GREEN + "Your bite has caused " + ChatColor.YELLOW + killed.getName() + ChatColor.GREEN + "to become a Vampire!");
					return;
				}
			}
		}
	}
}

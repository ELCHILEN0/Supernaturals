package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

public class VampireEvents implements SNEventListener {

	@SNEventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			if((Math.random()*100) < 5) {
				Player player = (Player) event.getEntity();
				SNPlayer snp = SupernaturalRaces.getPlayerManager().getPlayer(player);
				snp.setRemainingBleeding(snp.getRemainingBleeding() + 20*30);
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
}

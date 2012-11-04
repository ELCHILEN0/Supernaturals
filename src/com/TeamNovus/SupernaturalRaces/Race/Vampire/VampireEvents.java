package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class VampireEvents implements SNEventListener {

	@SNEventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		if((Math.random()*100) < 5) {
			if(event.getEntity() instanceof LivingEntity) {
				final LivingEntity e = (LivingEntity) event.getEntity();
				final Player p = event.getPlayer();

				final int tid = SupernaturalRaces.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(
						SupernaturalRaces.getPlugin(),
						new Runnable() {
							@Override
							public void run() {
								e.damage(2, p);
								if(e instanceof Player) ((Player) e).sendMessage(ChatColor.RED + "Bleeding...");
							}
						},
						20L * 5, 20L * 5);

				SupernaturalRaces.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(
						SupernaturalRaces.getPlugin(),
						new Runnable() {
							@Override
							public void run() {
								SupernaturalRaces.getPlugin().getServer().getScheduler().cancelTask(tid);
								if(e instanceof Player) ((Player) e).sendMessage(ChatColor.GREEN + "Your wounds have healed!");
							}
						},
						20L * 30);
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

package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEvents;

public class VampireEvents implements SNEvents {

	@Override
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

	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerDeath(PlayerDeathEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
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

	@Override
	public void onPlayerJoinRace(PlayerJoinRaceEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerLeaveRace(PlayerLeaveRaceEvent event) {
		// TODO Auto-generated method stub

	}
}

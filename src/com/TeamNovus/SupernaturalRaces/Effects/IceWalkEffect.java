package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTickEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Util.EffectUtil;

public class IceWalkEffect implements SNEventListener {

	@SNEventHandler
	public void onEffectBegin(EffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.GREEN + "You feel a chill at your feet!");
		}
	}
	
	@SNEventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		int radius = 1;
		for (int x = -(radius); x <= radius; x++) {
			for (int z = -(radius); z <= radius; z++) {
				Block block = event.getPlayer().getLocation().getBlock().getRelative(x, -1, z);
				if(block.getType().equals(Material.WATER) || block.getType().equals(Material.STATIONARY_WATER)) {
					block.setType(Material.ICE);
				}
			}
		}
	}
	
	@SNEventHandler
	public void onEffectTick(EffectTickEvent event) {
		if(event.getEntity() instanceof LivingEntity) {
			EffectUtil.addPotionGraphicalEffect((LivingEntity) event.getEntity(), 0x0000FF, 40);
		}
	}
	
	@SNEventHandler
	public void onEffectExpire(EffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.RED + "You can no longer walk on water!");
		}
	}
	
}

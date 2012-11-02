package com.TeamNovus.SupernaturalRaces.Race.Demon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class DemonRace implements SNRace {

	@Override
	public String name() {
		return "Demon";
	}

	@Override
	public Integer maxPower() {
		return 100;
	}
	
	@Override
	public Integer powerIncrement() {
		return 10;
	}

	@Override
	public List<SNSpell> spells() {
		List<SNSpell> spells = new ArrayList<SNSpell>();
		spells.add(new DemonicBlind());
		spells.add(new DemonicRage());
		return spells;
	}

	@Override
	public void onPlayerJoinRace(PlayerJoinRaceEvent event) {
		
	}

	@Override
	public void onPlayerLeaveRace(PlayerLeaveRaceEvent event) {
		
	}


	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {
		Player player = event.getPlayer();
		
		if(event.getCause().equals(DamageCause.FIRE_TICK)) {
			event.setCancelled(true);
		}
		
		if(event.getCause().equals(DamageCause.DROWNING)) {
			event.setDamage(event.getDamage()*3);
			return;
		}
		
		if(player.getLocation().getBlock().isLiquid()) {
			event.setDamage(event.getDamage()*2);
			return;
		}
	}
	
	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub
		
	}
}

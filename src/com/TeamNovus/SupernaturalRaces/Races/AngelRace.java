package com.TeamNovus.SupernaturalRaces.Races;

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
import com.TeamNovus.SupernaturalRaces.Spells.AngelicJump;

public class AngelRace implements SNRace {

	@Override
	public String name() {
		return "Angel";
	}

	@Override
	public Integer maxPower() {
		return 100;
	}

	@Override
	public List<SNSpell> spells() {
		List<SNSpell> spells = new ArrayList<SNSpell>();
		spells.add(new AngelicJump());
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
		
		if(event.getCause().equals(DamageCause.FALL)) {
			event.setCancelled(true);
		}
		
		if(player.getWorld().getTime() < 12000) {
			event.setDamage(event.getDamage()/2);
		}
		
		if(player.getWorld().getTime() > 12000) {
			event.setDamage(event.getDamage()*2);
		}
	}
	
	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub
		
	}
}

package com.TeamNovus.SupernaturalRaces.Races;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;
import com.TeamNovus.SupernaturalRaces.Spells.WolfHowl;

public class WerewolfRace implements SNRace {
	private SupernaturalRaces plugin;
	
	public WerewolfRace(SupernaturalRaces plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public String name() {
		return "Werewolf";
	}

	@Override
	public Integer maxPower() {
		return 75;
	}
	
	@Override
	public Integer powerIncrement() {
		return 20;
	}

	@Override
	public List<SNSpell> spells() {
		List<SNSpell> spells = new ArrayList<SNSpell>();
		spells.add(new WolfHowl(plugin));
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
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		if(event.getPlayer().getWorld().getTime() > 12000) {
			event.setDamage(event.getDamage()*2);
		}
		
	}
}

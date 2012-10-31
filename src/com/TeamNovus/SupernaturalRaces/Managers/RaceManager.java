package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Races.AngelRace;
import com.TeamNovus.SupernaturalRaces.Races.HumanRace;

public class RaceManager {
	private SupernaturalRaces plugin;
	private List<SNRace> races = new ArrayList<SNRace>();

	public RaceManager(SupernaturalRaces plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Register any races other than the default race.
	 * The Human races is returned for players without a race
	 * by default.
	 */
	public void registerRaces() {
		races.add(new AngelRace());
	}
	
	public List<SNRace> getRaces() {
		return races;
	}
	
	/**
	 * Set a players race
	 */
	public void setRace(SNPlayer player, SNRace race) {
		player.setRace(race.name());
	}
	
	/**
	 * Get the race a player is currently in
	 */
	public SNRace getRace(SNPlayer player) {
		for(SNRace race : races) {
			if(race.name().equalsIgnoreCase(player.getRace())) {
				return race;
			}
		}
		return new HumanRace();
	}
}

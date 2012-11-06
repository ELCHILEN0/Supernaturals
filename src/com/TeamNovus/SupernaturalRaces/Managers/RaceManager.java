package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Race.Angel.AngelRace;
import com.TeamNovus.SupernaturalRaces.Race.Demon.DemonRace;
import com.TeamNovus.SupernaturalRaces.Race.Human.HumanRace;
import com.TeamNovus.SupernaturalRaces.Race.Priest.PriestRace;
import com.TeamNovus.SupernaturalRaces.Race.Vampire.VampireRace;
import com.TeamNovus.SupernaturalRaces.Race.Werewolf.WerewolfRace;

public class RaceManager {
	private List<Race> races = new ArrayList<Race>();
	
	public RaceManager() {
		registerRaces();
	}
	
	/**
	 * Register any races other than the default race.
	 * The Human races is returned for players without a race
	 * by default.
	 */
	public void registerRaces() {
		races.add(new AngelRace());
		races.add(new DemonRace());
		races.add(new PriestRace());
		races.add(new VampireRace());
		races.add(new WerewolfRace());
		races.add(new HumanRace());
	}

	public List<Race> getRaces() {
		return races;
	}

	/**
	 * Set a players race
	 */
	public void setRace(SNPlayer player, Race race) {
		player.setRace(race.name());
	}

	/**
	 * Get the race a player is currently in
	 */
	public Race getRace(SNPlayer player) {
		for(Race race : races) {
			if(race.name().equalsIgnoreCase(player.getRace())) {
				return race;
			}
		}
		return new HumanRace();
	}
}

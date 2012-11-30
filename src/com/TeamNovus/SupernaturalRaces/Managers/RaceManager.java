package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.LinkedList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Race.Human.HumanRace;

public class RaceManager {
	private LinkedList<Race> races = new LinkedList<Race>();
	
	public RaceManager() {
		registerRaces();
	}
	
	/**
	 * Register any races other than the default race.
	 * The Human races is returned for players without a race
	 * by default.
	 */
	public void registerRaces() {
		races.add(new HumanRace());
//		races.add(new AngelRace());
//		races.add(new PriestRace());
//		races.add(new MageRace());
//		races.add(new DemonRace());
//		races.add(new NecromancerRace());
//		races.add(new VampireRace());
//		races.add(new WerewolfRace());
//		races.add(new RangerRace());
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
	
	/**
	 * Gets the best matching race by its name
	 */
	public Race getBestRace(String s) {
		for(Race race : races) {
			if(race.name().toLowerCase().startsWith(s.toLowerCase())) {
				return race;
			}
		}
		return null;
	}
	
	public Spell getBestSpell(String s) {
		for(Race race : races) {
			for(Spell spell : race.spells()) {
				if(spell.name().toLowerCase().startsWith(s.toLowerCase())) {
					return spell;
				}
			}
		}
		return null;
	}
}

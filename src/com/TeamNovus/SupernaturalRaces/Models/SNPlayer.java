package com.TeamNovus.SupernaturalRaces.Models;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;

public class SNPlayer extends SNAttributes {
	private String race = "Human";
	private Integer power = 0;
	private Integer boundSpellId = 0;
	
	public String getRace() {
		return race;
	}
	
	public Race getPlayerRace() {
		return SupernaturalRaces.getRaceManager().getBestRace(race);
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public void setRace(Race race) {
		this.race = race.name();
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}
	
	public void setBoundSpellId(Integer boundSpellId) {
		this.boundSpellId = boundSpellId;
	}
	
	public Integer getBoundSpellId() {
		return boundSpellId;
	}
}

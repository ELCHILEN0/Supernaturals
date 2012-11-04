package com.TeamNovus.SupernaturalRaces.Models;

import java.util.HashMap;

import com.TeamNovus.SupernaturalRaces.Metadata.SNAttribute;

public class SNPlayer {
	private String race = "Human";
	private Integer power = 0;
	private HashMap<String, SNAttribute> raceAttributes = new HashMap<String, SNAttribute>();
	
	public String getRace() {
		return race;
	}
	
	public void setRace(String race) {
		this.race = race;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}
	
	public SNAttribute getAttribute(String key) {
		return raceAttributes.get(key);
	}
	
	public void setAttribute(String key, SNAttribute value) {
		raceAttributes.put(key, value);
	}

	public HashMap<String, SNAttribute> getRaceAttributes() {
		return raceAttributes;
	}

	public void setRaceAttributes(HashMap<String, SNAttribute> raceAttributes) {
		this.raceAttributes = raceAttributes;
	}
}

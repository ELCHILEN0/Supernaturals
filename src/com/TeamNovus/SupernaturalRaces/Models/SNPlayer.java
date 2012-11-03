package com.TeamNovus.SupernaturalRaces.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Metadata.SNAttribute;

public class SNPlayer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String race = "Human";
	private Integer power = 50;
	private List<String> targets = new ArrayList<String>();
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
	
	public void setTargets(List<String> targets) {
		this.targets = targets;
	}
	
	public List<String> getTargets() {
		return targets;
	}
	
	public boolean isTarget(String s) {
		return targets.contains(s);
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

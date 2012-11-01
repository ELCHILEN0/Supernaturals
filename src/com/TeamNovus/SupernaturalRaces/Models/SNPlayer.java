package com.TeamNovus.SupernaturalRaces.Models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class SNPlayer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String race = "Human";
	private Integer power = 50;
	private List<String> targets;
	private HashMap<String, Object> raceAttributes;
	
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
	
	public Object getAttribute(String key) {
		return raceAttributes.get(key);
	}
	
	public void setAttribute(String key, Object value) {
		raceAttributes.put(key, value);
	}

	public HashMap<String, Object> getRaceAttributes() {
		return raceAttributes;
	}

	public void setRaceAttributes(HashMap<String, Object> raceAttributes) {
		this.raceAttributes = raceAttributes;
	}
}

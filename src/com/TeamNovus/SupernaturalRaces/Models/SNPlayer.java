package com.TeamNovus.SupernaturalRaces.Models;

public class SNPlayer extends SNAttributes {
	private String race = "Human";
	private Integer power = 0;
	
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
}

package com.TeamNovus.SupernaturalRaces.Character;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;

public class SNPlayer {
	private String name;
	private String race;
	private Integer power;
	private Integer boundSpellId;
	
	public SNPlayer() { }
	
	public SNPlayer(Player player) {
		this.name = player.getName();
	}
	
	public SNPlayer(String name) {
		this.name = name;
	}
	
	public String getRace() {
		return race;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public SNRace getPlayerRace() {
		return SupernaturalRaces.getRaceManager().getBestRace(race);
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public void setRace(SNRace race) {
		setRace(race.name());
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
	
	// Bukkit Stuff:
	public Player getPlayer() {
		return Bukkit.getServer().getPlayerExact(name);
	}
	
	public Boolean isOnline() {
		return Bukkit.getServer().getPlayerExact(name).isOnline();
	}
	
	@Override
	public String toString() {
		return "SNPlayer [name=" + name + ", race=" + race + ", power=" + power
				+ ", boundSpellId=" + boundSpellId + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SNPlayer other = (SNPlayer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

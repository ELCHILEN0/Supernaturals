package com.TeamNovus.Supernaturals.Player.Statistics;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.Id;
import com.TeamNovus.Supernaturals.Database.Database;
import com.TeamNovus.Supernaturals.Player.Class.Power;

@Table(name = "sn_player_cooldowns")
public class Cooldown {	
	public static final String ID 				= "id";
	public static final String PLAYER_ID 		= "player_id";
	public static final String POWER 			= "power";
	public static final String REMAINING 		= "remaining";
	
	@Id
	@Column(name = ID)
	private Integer id;
	
	@Column(name = PLAYER_ID)
	private Integer playerId;
	
	@Column(name = POWER)
	private String power;
	
	@Column(name = REMAINING)
	private Integer remaining;
	
	public Cooldown() { }
	
	public Cooldown(String power, Integer remaining) {
		this.power = power;
		this.remaining = remaining;
	}
	
	public Cooldown(Power power, Integer remaining) {
		this.power = power.getName();
		this.remaining = remaining;
	}
		
	public boolean save() {
		return Database.save(this);
	}
	
	public boolean delete() {
		return Database.drop(this);
	}
	
	public Integer getId() {
		if(id == null) {
			save();
		}
		
		return id;
	}
	
	public Cooldown setPlayerId(Integer playerId) {
		this.playerId = playerId;
		
		return this;
	}
	
	public String getPower() {
		return power;
	}
	
	public Cooldown setPower(String power) {
		this.power = power;
		
		return this;
	}
	
	public Integer getRemaining() {
		return remaining;
	}
	
	public Cooldown setRemaining(Integer remaining) {
		this.remaining = remaining;
		
		return this;
	}

	// Java:
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cooldown other = (Cooldown) obj;
		if (power == null) {
			if (other.power != null) {
				return false;
			}
		} else if (!power.equals(other.power)) {
			return false;
		}
		return true;
	}
}

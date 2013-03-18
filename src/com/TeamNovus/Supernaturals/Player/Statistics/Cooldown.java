package com.TeamNovus.Supernaturals.Player.Statistics;

import java.io.Serializable;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.ForeignKey;
import com.TeamNovus.Persistence.Annotations.Columns.Id;
import com.TeamNovus.Supernaturals.Player.Class.Power;

@Table(name = "sn_player_cooldowns")
public class Cooldown implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@ForeignKey
	@Column(name = "player_id")
	private Integer playerId;
	
	@Column(name = "power")
	private String power;
	
	@Column(name = "remaining_ticks")
	private Integer remainingTicks;
	
	public Cooldown() { }
	
	public Cooldown(String power, Integer remainingTicks) {
		this.power = power;
		this.remainingTicks = remainingTicks;
	}
	
	public Cooldown(Power power, Integer remainingTicks) {
		this.power = power.getName();
		this.remainingTicks = remainingTicks;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPower() {
		return power;
	}
	
	public void setPower(String power) {
		this.power = power;
	}
	
	public Integer getRemainingTicks() {
		return remainingTicks;
	}
	
	public void setRemainingTicks(Integer remainingTicks) {
		this.remainingTicks = remainingTicks;
	}

	// Java:
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		return result;
	}

	@Override
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

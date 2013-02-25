package com.TeamNovus.Supernaturals.Player.Statistics;

import java.io.Serializable;

import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Cooldown implements Serializable {
	private static final long serialVersionUID = 1L;
		
	private String power;
	
	private int remainingTicks;
	
	public Cooldown(String power, int remainingTicks) {
		this.power = power;
		this.remainingTicks = remainingTicks;
	}
	
	public Cooldown(Power power, int remainingTicks) {
		this.power = power.getName();
		this.remainingTicks = remainingTicks;
	}
	
	public String getPower() {
		return power;
	}
	
	public void setPower(String power) {
		this.power = power;
	}
	
	public int getRemainingTicks() {
		return remainingTicks;
	}
	
	public void setRemainingTicks(int remainingTicks) {
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

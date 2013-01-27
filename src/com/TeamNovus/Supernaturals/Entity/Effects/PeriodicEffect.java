package com.TeamNovus.Supernaturals.Entity.Effects;

public class PeriodicEffect extends Effect {
	protected Integer period;
	
	public PeriodicEffect(Integer period) {
		this.period = period;
	}
	
	public Integer getPeriod() {
		return period;
	}
	
	public void setPeriod(Integer period) {
		this.period = period;
	}

}

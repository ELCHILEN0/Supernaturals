package com.TeamNovus.Supernaturals.Entity.Effects;

public class LastingPeriodicEffect extends LastingEffect {

	protected Integer period;
	
	public LastingPeriodicEffect(Integer duration, Integer period) {
		super(duration);
		this.period = period;
	}
	
	public Integer getPeriod() {
		return period;
	}
	
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
}

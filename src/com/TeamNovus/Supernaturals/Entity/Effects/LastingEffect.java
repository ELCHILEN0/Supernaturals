package com.TeamNovus.Supernaturals.Entity.Effects;

public class LastingEffect extends Effect {
	protected Integer duration;
		
	public LastingEffect(Integer duration) {
		this.duration = duration;
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
}

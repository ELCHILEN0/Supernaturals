package com.TeamNovus.SupernaturalRaces.Character;

import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class SNEffect {
	private Integer delay;
	private Integer duration;
	private Integer period;
	private List<Class<? extends SNEventListener>> modifiers;
	
	public SNEffect(Integer delay, Integer duration, List<Class<? extends SNEventListener>> modifiers) {
		this.delay = delay;
		this.duration = duration;
		this.period = 1;
		this.modifiers = modifiers;
	}
	
	public SNEffect(Integer delay, Integer duration, Integer period, List<Class<? extends SNEventListener>> modifiers) {
		this.delay = delay;
		this.duration = duration;
		this.period = period;
		this.modifiers = modifiers;
	}
	
	public Integer getDelay() {
		return delay;
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	public Integer getPeriod() {
		return period;
	}
	
	public List<Class<? extends SNEventListener>> getModifiers() {
		return modifiers;
	}
}

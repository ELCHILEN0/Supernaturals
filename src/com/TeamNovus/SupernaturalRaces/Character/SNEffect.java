package com.TeamNovus.SupernaturalRaces.Character;

import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class SNEffect {
	public enum SNEffectType {
		NORMAL, HEAL, DAMAGE; 
	}
	
	private Integer delay;
	private Integer duration;
	private Integer period;
	private Class<? extends SNEventListener> modifiers;
	private SNEffectType type;
	
	public SNEffect(Integer delay, Integer duration, Class<? extends SNEventListener> modifiers) {
		this.delay = delay;
		this.duration = duration;
		this.period = 1;
		this.modifiers = modifiers;
		this.type = SNEffectType.NORMAL;
	}
	
	public SNEffect(Integer delay, Integer duration, Integer period, Class<? extends SNEventListener> modifiers, SNEffectType type) {
		this.delay = delay;
		this.duration = duration;
		this.period = period;
		this.modifiers = modifiers;
		this.type = type;
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
	
	public Class<? extends SNEventListener> getModifiers() {
		return modifiers;
	}

	public SNEffectType getType() {
		return type;
	}
}

package com.TeamNovus.SupernaturalRaces.Character;

import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class SNEffect {
	public enum SNEffectType {
		NORMAL, DAMAGE, HEAL, FIRE, BLEED, PHASE_WALK;
	}
	
	private Integer elapsed = 0;
	
	private Integer delay;
	private Integer duration;
	private Integer period;
	private Integer amplifier;
	
	private Class<? extends SNEventListener> modifiers;
	
	private SNEffectType type;

	public SNEffect(Integer delay, Integer duration, Integer period, Integer amplifier, Class<? extends SNEventListener> modifiers, SNEffectType type) {
		this.delay = delay;
		this.duration = duration;
		this.period = period;
		this.amplifier = amplifier;
		this.modifiers = modifiers;
		this.type = type;
	}
	
	public SNEffect(Integer duration, Integer period, Integer amplifier, Class<? extends SNEventListener> modifiers, SNEffectType type) {
		this.delay = 0;
		this.duration = duration;
		this.period = period;
		this.amplifier = amplifier;
		this.modifiers = modifiers;
		this.type = type;
	}
	
	public SNEffect(Integer period, Integer amplifier, Class<? extends SNEventListener> modifiers, SNEffectType type) {
		this.delay = 0;
		this.duration = -1;
		this.period = period;
		this.amplifier = amplifier;
		this.modifiers = modifiers;
		this.type = type;
	}
	
	public SNEffect(Integer amplifier, Class<? extends SNEventListener> modifiers, SNEffectType type) {
		this.delay = 0;
		this.duration = -1;
		this.period = 1;
		this.amplifier = amplifier;
		this.modifiers = modifiers;
		this.type = type;
	}
	
	public SNEffect(Class<? extends SNEventListener> modifiers, SNEffectType type) {
		this.delay = 0;
		this.duration = -1;
		this.period = 1;
		this.amplifier = 1;
		this.modifiers = modifiers;
		this.type = type;
	}
	
	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public Integer getElapsed() {
		return elapsed;
	}

	public void setElapsed(Integer elapsed) {
		this.elapsed = elapsed;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getAmplifier() {
		return amplifier;
	}

	public void setAmplifier(Integer amplifier) {
		this.amplifier = amplifier;
	}

	public Class<? extends SNEventListener> getModifiers() {
		return modifiers;
	}

	public void setModifiers(Class<? extends SNEventListener> modifiers) {
		this.modifiers = modifiers;
	}

	public SNEffectType getType() {
		return type;
	}

	public void setType(SNEffectType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SNEffect [elapsed=" + elapsed + ", delay=" + delay
				+ ", duration=" + duration + ", period=" + period
				+ ", amplifier=" + amplifier + ", modifiers=" + modifiers
				+ ", type=" + type + "]";
	}
}

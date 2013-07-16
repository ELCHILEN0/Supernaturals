package com.TeamNovus.Supernaturals.Custom.Effect;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.ForeignKey;

@Table(name = "sn_effects")
public class Effect {
	@ForeignKey
	private Integer entityId;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "elapsed")
	private Integer elapsed;
	
	@Column(name = "duration")
	private Integer duration;
	
	@Column(name = "period")
	private Integer period;
	
	@Column(name = "amplifier")
	private Integer amplifier;
	
	public Effect(EffectType type) {
		this.type = type.getId();
		
		this.period = 0;
		this.duration = 0;
		this.elapsed = 0;
		this.amplifier = 0;
	}
	
	public Effect(EffectType type, Integer duration) {
		this(type);
		
		this.duration = duration;
	}
	
	public Effect(EffectType type, Integer duration, Integer amplifier) {
		this(type, duration);
		
		this.amplifier = amplifier;
	}
	
	public Effect(EffectType type, Integer duration, Integer period, Integer amplifier) {
		this(type, duration);
		
		this.period = period;
		this.amplifier = amplifier;
	}
	
	public Integer getEntityId() {
		return entityId;
	}
	
	public Integer getElapsed() {
		return elapsed;
	}
	
	public Effect setElapsed(Integer elapsed) {
		this.elapsed = elapsed;
		
		return this;
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	public Effect setDuration(Integer duration) {
		this.duration = duration;
		
		return this;
	}
	
	public Integer getPeriod() {
		return period;
	}
	
	public Effect setPeriod(Integer period) {
		this.period = period;
		
		return this;
	}
	
	public Integer getAmplifier() {
		return amplifier;
	}
	
	public Effect setAmplifier(Integer amplifier) {
		this.amplifier = amplifier;
		
		return this;
	}
	
	public EffectType getType() {
		return EffectType.getEffectType(type);
	}
	
	public boolean isLasting() {
		return duration > 0;
	}
	
	public boolean isPeriodic() {
		return period > 0;
	}
	
	public boolean isExpired() {
		return elapsed >= duration;
	}
	
	public boolean isActive() {
		return !(isExpired());
	}
	
	public void end() {
		elapsed = duration;
	}
	
	public void tick() {
		if(isLasting()) {
			elapsed++;
		}
	}
}

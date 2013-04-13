package com.TeamNovus.Supernaturals.Entity;

public class Effect {
//	private Integer id;
//	private Integer entityId;
	protected Integer elapsed;
	protected Integer duration;
	protected Integer period;
	
	public Effect() {
		this.elapsed = 0;
		this.duration = 0;
		this.period = 0;
	}
	
	public Effect(Integer duration, Integer period) {
		this();
		
		this.duration = duration;
		this.period = period;
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
	
	public boolean isLasting() {
		return duration > 0;
	}
	
	public boolean isPeriodic() {
		return period > 0;
	}
	
	public boolean isComplete() {
		return elapsed > duration;
	}
	
	public boolean isRunning() {
		return !(isComplete());
	}

	public void tick() {
		if(isLasting()) {
			elapsed++;
		}
	}
}

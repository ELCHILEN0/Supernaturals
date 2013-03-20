package com.TeamNovus.Supernaturals.Entity;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.ForeignKey;
import com.TeamNovus.Persistence.Annotations.Columns.Id;

@Table(name = "sn_effects")
public class Effect {
	@Id
	@Column(name = "id")
	private Integer id;
	
	@ForeignKey
	@Column(name = "entity_id")
	private Integer entityId;
	
	@Column(name = "elapsed")
	protected Integer elapsed;
	
	@Column(name = "duration")
	protected Integer duration;
	
	@Column(name = "period")
	protected Integer period;
	
	public Effect() {
		this.elapsed = 0;
		this.duration = 0;
		this.period = 0;
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

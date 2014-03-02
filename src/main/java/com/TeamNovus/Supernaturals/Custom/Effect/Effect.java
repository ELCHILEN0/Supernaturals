package com.TeamNovus.Supernaturals.Custom.Effect;

import static com.TeamNovus.Persistence.Queries.Expression.Expressions.equal;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.Id;
import com.TeamNovus.Supernaturals.Database.Database;

@Table(name = "sn_effects")
public class Effect {
	public static final String ID 			= "id";
	public static final String ENTITY_ID 	= "entity_id";
	public static final String TYPE 		= "type";
	public static final String ELAPSED 		= "elapsed";
	public static final String DURATION 	= "duration";
	public static final String PERIOD 		= "period";
	public static final String AMPLIFIER 	= "amplifier";
	
	@Id
	@Column(name = ID)
	private Integer id;
	
	@Column(name = ENTITY_ID)
	private Integer entityId;
	
	@Column(name = TYPE)
	private Integer type;
	
	@Column(name = ELAPSED)
	private Integer elapsed;
	
	@Column(name = DURATION)
	private Integer duration;
	
	@Column(name = PERIOD)
	private Integer period;
	
	@Column(name = AMPLIFIER)
	private Integer amplifier;
	
	public static List<Effect> getAllEntities() {
		return Database.findAll(Effect.class);
	}
	
	public static Effect getEntity(int id) {
		return Database.find(Effect.class, id);
	}
	
	public static List<Effect> getEffects(LivingEntity e) {
		return Database.select(Effect.class).where(equal(ENTITY_ID, e.getUniqueId())).findList();
	}
	
	public boolean save() {
		return Database.save(this);
	}
	
	public boolean delete() {
		return Database.drop(this);
	}
	
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
	
	public int getId() {
		if(id == null) {
			save();
		}
		
		return id;
	}
	
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
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

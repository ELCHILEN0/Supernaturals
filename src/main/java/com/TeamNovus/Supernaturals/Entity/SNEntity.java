package com.TeamNovus.Supernaturals.Entity;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.Id;
import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Database.Database;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTriggerEvent;

import static com.TeamNovus.Persistence.Queries.Expression.Expressions.*;

@Table(name = "sn_entities")
public class SNEntity {
	public static final String ID 	= "ID";
	public static final String UUID = "UUID";
	
	@Id
	@Column(name = ID)
	private Integer id;
	
	@Column(name = UUID)
	private String uuid;
	
	public static List<SNEntity> getAllEntities() {
		return Database.findAll(SNEntity.class);
	}
	
	public static SNEntity getEntity(int id) {
		return Database.find(SNEntity.class, id);
	}
		
	public static boolean isAttached(LivingEntity entity) {
		return Database.select(SNEntity.class).where(equal(UUID, entity.getUniqueId().toString())).findOne() == null;
	}
	
	public static SNEntity getEntity(LivingEntity entity) {
		SNEntity e = Database.select(SNEntity.class).where(equal(UUID, entity.getUniqueId().toString())).findOne();
		
		if(e == null) {
			e = new SNEntity(entity);
			e.save();
		}
		
		return e;
	}
	
	public boolean save() {
		return Database.save(this);
	}
	
	public boolean delete() {
		return Database.drop(this);
	}
	
	// Empty constructor for reflection.
	public SNEntity() {  }
	
	public SNEntity(LivingEntity e) {
		uuid = e.getUniqueId().toString();
	}
	
	public int getId() {
		if(id == null) {
			save();
		}
		
		return id;
	}
	
	public UUID getUUID() {
		return java.util.UUID.fromString(uuid);
	}

	public LivingEntity getEntity() {
		for (World w : Bukkit.getWorlds()) {
			for (LivingEntity e : w.getLivingEntities()) {
				if (e.getUniqueId().toString().equals(uuid)) {
					return e;
				}
			}
		}

		return null;
	}

	public Boolean isAlive() {
		return getEntity() != null;
	}

	public List<Effect> getEffects() {
		return Database.select(Effect.class).where(equal(ID, getId())).findList();
	}

	public void addEffect(Effect effect) {
		effect.setEntityId(getId());
		
		Iterator<Effect> effectIterator = getEffects().iterator();
		while (effectIterator.hasNext()) {
			Effect e = effectIterator.next();
			if (e.getClass().equals(effect.getClass())) {				
				e = effect;
				e.save();
				return;
			}
		}

		effect.save();
	}

	public void removeEffect(Effect effect) {
		Iterator<Effect> effectIterator = getEffects().iterator();
		while (effectIterator.hasNext()) {
			Effect e = effectIterator.next();
			if (e.getClass().equals(effect.getClass())) {				
				e.delete();
			}
		}
	}
	
	public boolean hasEffect(EffectType type) {
		for(Effect effect : getEffects()) {
			if(type.equals(effect.getType())) {
				return true;
			}
		}
		
		return false;
	}

	public void tick() {
		if(!(isAlive())) return;

		Iterator<Effect> effectIterator = getEffects().iterator();
		while (effectIterator.hasNext()) {
			Effect effect = effectIterator.next();
			
			Bukkit.getPluginManager().callEvent(new EntityEffectTickEvent(getEntity(), effect));
			
			if (effect.isLasting()) {		
				if (effect.getElapsed() == 0) {
					Bukkit.getPluginManager().callEvent(new EntityEffectBeginEvent(getEntity(), effect));
				}

				if (effect.isExpired()) {
					Bukkit.getPluginManager().callEvent(new EntityEffectExpireEvent(getEntity(), effect));
					effect.delete();
					continue;
				}				
			}
			
			if (effect.isPeriodic()) {				
				if (effect.getElapsed() % effect.getPeriod() == 0) {
					Bukkit.getPluginManager().callEvent(new EntityEffectTriggerEvent(getEntity(), effect));
				}
			}
			
			effect.setElapsed(effect.getElapsed() + 1);			
			effect.save();
		}
		
		if(getEffects().size() == 0) {
			delete();
		}
	}

}

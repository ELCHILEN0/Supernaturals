package com.TeamNovus.Supernaturals.Custom.Effect;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes.*;

public abstract class EffectType {
	public static List<EffectType> types = new ArrayList<EffectType>();

	/*
Confusion
Hunger
Regen
Bleed
Mana Regen
Mana Drain
Night Vision
Scare
Target
Water Breathe
Water Walk
Jumping
Fire Resistance
Phoenix Rise
Blind Attacker
	 */
	
	// Effects:
	public static final EffectType BARRAGE 		= new Barrage();
	public static final EffectType STRENGTH 	= new Strength();
	public static final EffectType CRITICAL		= new Critical();
	
    public static final EffectType BLOCK		= new Block();
	public static final EffectType EVASION 		= new Evasion();
	public static final EffectType RESISTANCE 	= new Resistance();

	public static final EffectType POISON_ARROW 	= new PoisonArrow();
	public static final EffectType BLINDING_ARROW	= new BlindingArrow();
	
	public static final EffectType SNEAK 		= new Sneak();
	public static final EffectType INVISIBILITY = new Invisibility();
	
	public static final EffectType METABOLISM 	= new Metabolism();
	public static final EffectType SPEED 		= new Speed();
	
    public static final EffectType SECOND_WIND	= new SecondWind();

	private Integer id;
	
	public EffectType() {
		this.id = types.size();
		
		types.add(this);
	}
	
	public Integer getId() {
		return id;
	}

	public static EffectType getEffectType(int id) {
		for(EffectType type : types) {
			if(id == type.getId()) {
				return type;
			}
		}
		
		return null;
	}
}

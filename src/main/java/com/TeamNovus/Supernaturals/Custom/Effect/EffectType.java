package com.TeamNovus.Supernaturals.Custom.Effect;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes.*;

public abstract class EffectType {
	public static List<EffectType> types = new ArrayList<EffectType>();

	/*
	Resistance
Weakness
Strength
Speed
Slowness
Sneak
Invisibility
Confusion
Invulnerability
Hunger
Metabolism
Regen
Bleed
Mana Regen
Mana Drain
Night Vision
Scare
Target
Swiftness
Counterattack
Water Breathe
Water Walk
Jumping
Fire Resistance
Phoenix Rise
Blind Attacker
Poison Arrows
Blind Arrows
	 */
	
	// Effects:
	public static final EffectType RESISTANCE 	= new Resistance();
	public static final EffectType WEAKNESS 	= new Weakness();
	public static final EffectType STRENGTH 	= new Strength();
	public static final EffectType SPEED 		= new Speed();
	public static final EffectType SLOWNESS 	= new Slowness();
	public static final EffectType SNEAK 		= new Sneak();
	public static final EffectType EVASION 		= new Evasion();
	public static final EffectType POISON_ARROW 	= new PoisonArrow();
	public static final EffectType BLINDING_ARROW	= new BlindingArrow();
	public static final EffectType INVISIBILITY = new Invisibility();
	public static final EffectType CRITICAL		= new Critical();
	public static final EffectType CONFUSION	= new Confusion();
    public static final EffectType REVIVE		= new Revive();
    public static final EffectType SHIELD		= new Shield();

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

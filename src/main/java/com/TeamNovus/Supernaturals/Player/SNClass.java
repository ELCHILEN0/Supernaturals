package com.TeamNovus.Supernaturals.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.Class.Ability;
import com.TeamNovus.Supernaturals.Player.Class.Power;
import com.google.common.collect.LinkedListMultimap;

public abstract class SNClass {	
	private String name;
	private ChatColor color;
	private Integer maxLevel;
	
	private HashMap<Integer, Float> speed = new HashMap<Integer, Float>(); 
	private HashMap<Integer, Integer> maxMana = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> maxHealth = new HashMap<Integer, Integer>(); 
	private HashMap<Integer, Integer> maxFoodLevel = new HashMap<Integer, Integer>(); 
	private LinkedListMultimap<Integer, Power> powers = LinkedListMultimap.create();
	private LinkedListMultimap<Integer, Ability> abilities = LinkedListMultimap.create();
	
	private SNClass parentClass;
	private LinkedListMultimap<Integer, SNClass> joinableClasses = LinkedListMultimap.create();
	
	public SNClass(String name, ChatColor color, Integer maxLevel, SNClass parentClass) {
		this.name = name;
		this.color = color;
		this.maxLevel = maxLevel;
		this.parentClass = parentClass;
		
		// Defaults:
		maxMana.put(1, 0);
		maxHealth.put(1, 20);
		maxFoodLevel.put(1, 20);
		speed.put(1, 0.2f);
	}
	
	public SNClass(String name, ChatColor color, Integer maxLevel) {
		this(name, color, maxLevel, null);
	}
	
	/**
	 * Gets the name of the class.
	 * 
	 * @return The name of the class.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the color for the class.
	 * 
	 * @return The color for the class.
	 */
	public ChatColor getColor() {
		return color;
	}
	
	/**
	 * Gets the max level for the class.
	 * 
	 * @return The max level for the class.
	 */
	public Integer getMaxLevel() {
		return maxLevel;
	}
	
	/**
	 * Gets the parent class for the class.
	 * 
	 * @return The parent class for the class.
	 */
	public SNClass getParentClass() {
		return parentClass;
	}
		
	public Integer getMaxMana(Integer level) {
		int l = 0;
		
		for(Integer i : maxMana.keySet()) {
			if(i <= level && i > l) {
				l = i;
			}
		}
		
		return maxMana.get(l);
	}
	
	public void setMaxMana(Integer level, Integer maxMana) {
		this.maxMana.put(level, maxMana);
	}
	
	public Integer getMaxHealth(Integer level) {
		int l = 0;
		
		for(Integer i : maxHealth.keySet()) {
			if(i <= level && i > l) {
				l = i;
			}
		}
		
		return maxHealth.get(l);
	}
	
	public void setMaxHealth(Integer level, Integer maxHealth) {
		this.maxHealth.put(level, maxHealth);
	}
	
	public Integer getMaxFoodLevel(Integer level) {
		int l = 0;
		
		for(Integer i : maxFoodLevel.keySet()) {
			if(i <= level && i > l) {
				l = i;
			}
		}
		
		return maxFoodLevel.get(l);
	}
	
	public void setMaxFoodLevel(Integer level, Integer maxFoodLevel) {
		this.maxFoodLevel.put(level, maxFoodLevel);
	}
	
	public Float getSpeed(Integer level) {
		int l = 0;
		
		for(Integer i : speed.keySet()) {
			if(i <= level && i > l) {
				l = i;
			}
		}
		
		return speed.get(l);
	}
	
	public void setSpeed(Integer level, Float speed) {
		this.speed.put(level, speed);
	}
	
	public List<Power> getPowers(Integer level) {
		List<Power> data = new ArrayList<Power>();
		
		// Iterate through all the registered powers and levels.
		for (Integer i : powers.keySet()) {
			if (i > level) break;
			
			// Setup the iterators
			Iterator<Power> toAddIterator = powers.get(i).iterator();
			
			// Loop through the powers of a level.
			while (toAddIterator.hasNext()) {
				Power t = toAddIterator.next();
				
				Iterator<Power> dataIterator = data.iterator();
				
				// Make sure there isn't an existing power with the same level.
				while (dataIterator.hasNext()) {
					Power d = dataIterator.next();
					
					if (d.getClass().equals(t.getClass())) {
						dataIterator.remove();
					}
				}
				
				data.add(t);
			}
		}
		
		return data;
	}
	
	public List<Power> getUniquePowers(Integer level) {
		return powers.get(level);
	}
	
	public void addPower(Integer level, Power power) {
		powers.put(level, power);
	}
	
	public List<Ability> getAbilities(Integer level) {
		List<Ability> data = new ArrayList<Ability>();
		
		// Iterate through all the registered abilities and levels.
		for (Integer i : abilities.keySet()) {
			if (i > level) break;
			
			// Setup the iterators
			Iterator<Ability> toAddIterator = abilities.get(i).iterator();
			
			// Loop through the abilities of a level.
			while (toAddIterator.hasNext()) {
				Ability t = toAddIterator.next();
				
				Iterator<Ability> dataIterator = data.iterator();
				
				// Make sure there isn't an existing ability with the same level.
				while (dataIterator.hasNext()) {
					Ability d = dataIterator.next();
					
					if (d.getType().getClass().equals(t.getType().getClass())) {
						dataIterator.remove();
					}
				}

				data.add(t);
			}
		}
		
		return data;
	}
	
	public List<Ability> getUniqueAbilities(Integer level) {
		return abilities.get(level);
	}
	
	public void addAbility(Integer level, Ability ability) {
		abilities.put(level, ability);
	}
	
	public List<SNClass> getJoinableClasses(Integer level) {
		List<SNClass> data = new ArrayList<SNClass>();
		
		// Iterate through all the registered abilities and levels.
		for (Integer i : joinableClasses.keySet()) {
			if (i > level) break;
			
			// Setup the iterators
			Iterator<SNClass> toAddIterator = joinableClasses.get(i).iterator();
			
			// Loop through the abilities of a level.
			while (toAddIterator.hasNext()) {
				SNClass t = toAddIterator.next();
				
				Iterator<SNClass> dataIterator = data.iterator();
				
				// Make sure there isn't an existing ability with the same level.
				while (dataIterator.hasNext()) {
					SNClass d = dataIterator.next();
					
					if (d.getClass().equals(t.getClass())) {
						dataIterator.remove();
					}
				}

				data.add(t);
			}
		}
		
		return data;
	}
	
	public List<SNClass> getAllJoinableClasses() {
		return joinableClasses.values();
	}
	
	public void addJoinableClass(Integer level, SNClass joinableClass) {
		joinableClasses.put(level, joinableClass);
	}
	
	public int getLevelRequiredForClass(SNClass joinableClass) {
		for (Entry<Integer, SNClass> entry : joinableClasses.entries()) {
			if(entry.getValue().getName().equals(joinableClass.getName())) {
				return entry.getKey();
			}
		}
		
		return -1;
	}
	
	public Boolean hasChangedFrom(Integer last, Integer current) {
		return getSpeed(last) != getSpeed(current) || getMaxMana(last) != getMaxMana(current) ||
														getMaxHealth(last) != getMaxHealth(current) || 
														getMaxFoodLevel(last) != getMaxFoodLevel(current) ||
														!(getPowers(last).equals(getPowers(current))) ||
														!(getAbilities(last).equals(getAbilities(current))) ||
														!(getJoinableClasses(last).equals(getJoinableClasses(current)));
	}

	// Java:
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SNClass other = (SNClass) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}	
}

package com.TeamNovus.Supernaturals.Player;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.SNEntities;
import com.TeamNovus.Supernaturals.Classes.Human;
import com.TeamNovus.Supernaturals.Collections.Entity;
import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Events.PlayerChangeClassEvent;
import com.TeamNovus.Supernaturals.Player.Class.Ability;
import com.TeamNovus.Supernaturals.Player.Class.Power;
import com.TeamNovus.Supernaturals.Util.SNClassUtil;

public class SNPlayer extends Entity {
	private String name;

	// Data Values:
	private Integer mana;
	private Integer maxMana;
	private Integer health;
	private Integer maxHealth;
	private Integer foodLevel;
	private Integer maxFoodLevel;
	private Float speed;

	// Class:
	private SNClass playerClass;
	private Integer binding;

	// Powers:
	private HashMap<Class<? extends Power>, Long> cooldowns = new HashMap<Class<? extends Power>, Long>();
	
	// Leveling:
	private Integer experience;
	private Integer attributePoints;

	// Statistics:
	private Integer strengthStat;
	private Integer resistanceStat;
	private Integer dexterityStat;
	private Integer magicStat;

	public SNPlayer() {
		// Data Values:
		this.mana = 0;
		this.health = 20;
		this.foodLevel = 20;
		this.speed = 0.2f;

		this.maxMana = 0;
		this.maxHealth = 20;
		this.maxFoodLevel = 20;

		// Class:
		this.playerClass = new Human();
		this.binding = 0;

		// Leveling:
		this.experience = 0;
		this.attributePoints = 0;

		// Statistics:
		this.strengthStat = 0;
		this.resistanceStat = 0;
		this.dexterityStat = 0;
		this.magicStat = 0;		
	}

	public SNPlayer(Player p) { 
		this();
		this.name = p.getName();
	}

	/**
	 * Gets the players name.
	 * 
	 * @return The players name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the players name.
	 * 
	 * @param name - The players new name. 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the players current mana.
	 * 
	 * @return Integer with the players mana.
	 */
	public Integer getMana() {
		return mana;
	}

	/**
	 * Sets the players current mana.
	 * 
	 * @param mana - The players new mana.
	 */
	public void setMana(Integer mana) {
		this.mana = mana;
	}

	/**
	 * Gets the players maximum mana.
	 * 
	 * @return The players maximum mana.
	 */
	public Integer getMaxMana() {
		return maxMana;
	}

	/**
	 * Sets the players maximum mana.
	 * 
	 * @param maxMana - The players new maximum mana.
	 */
	public void setMaxMana(Integer maxMana) {
		this.maxMana = maxMana;
	}

	/**
	 * Gets the players health.
	 * 
	 * @return The players current health.
	 */
	public Integer getHealth() {
		reSync();
		
		return health;
	}

	/**
	 * Sets the players health.
	 * 
	 * @param health - The players new health.
	 */
	public void setHealth(Integer health) {
		this.health = health;

		updateHealth();
	}

	/**
	 * Gets the players maximum health.
	 * 
	 * @return The players maximum health.
	 */
	public Integer getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Sets the players maximum health.
	 * 
	 * @param maxHealth - The players new maximum health.
	 */
	public void setMaxHealth(Integer maxHealth) {
		this.maxHealth = maxHealth;

		updateHealth();
	}

	/**
	 * Updates the players health bar to @health.
	 * 
	 */
	public void updateHealth() {		
		if(maxHealth <= 0)
			maxHealth = 20;

		if(health > maxHealth)
			health = maxHealth;

		if(health < 0)
			health = 0;

		// This synchronizes the players health to their health bar.
		if(isOnline())
			getPlayer().setHealth((int) Math.ceil((health * 20)/maxHealth));
	}

	/**
	 * Gets the players food level.
	 * 
	 * @return The players food level.
	 */
	public Integer getFoodLevel() {
		reSync();

		return foodLevel;
	}

	/**
	 * Sets the players food level.
	 * 
	 * @param foodLevel - The players new food level.
	 */
	public void setFoodLevel(Integer foodLevel) {
		this.foodLevel = foodLevel;

		updateFoodLevel();
	}

	/**
	 * Gets the players maximum food level.
	 * 
	 * @return The players maximum food level.
	 */
	public Integer getMaxFoodLevel() {
		return maxFoodLevel;
	}

	/**
	 * Sets the players maximum food level.
	 * 
	 * @param maxFoodLevel - The players new maximum food leve.
	 */
	public void setMaxFoodLevel(Integer maxFoodLevel) {
		this.maxFoodLevel = maxFoodLevel;

		updateFoodLevel();
	}

	/**
	 * Updates the players hunger bar to @foodLevel.
	 * 
	 */
	public void updateFoodLevel() {
		if(maxFoodLevel <= 0)
			maxFoodLevel = 20;

		if(foodLevel > maxFoodLevel)
			foodLevel = maxFoodLevel;

		if(foodLevel < 0)
			foodLevel = 0;

		// This synchronizes the players food level to their hunger bar.
		if(isOnline())
			getPlayer().setFoodLevel((int) Math.ceil((foodLevel * 20)/maxFoodLevel));
	}

	/**
	 * Gets the players walking speed.
	 * 
	 * @return The players speed.
	 */
	public Float getSpeed() {
		reSync();
		
		return speed;
	}

	/**
	 * Sets the players walking speed.
	 * 
	 * @param speed - The players new walking speed.
	 */
	public void setSpeed(Float speed) {
		this.speed = speed;

		updateSpeed();
	}

	/**
	 * Updates the players speed.
	 */
	public void updateSpeed() {
		if(isOnline()) {
			getPlayer().setWalkSpeed(speed);

			// Hack to ensure that the speed gets applied to the client.
			getPlayer().saveData();
			getPlayer().loadData();
		}
	}

	/**
	 * Updates the players client to the actual data values.
	 * Updates: @health, @foodLevel, @speed
	 * 
	 */
	public void update() {
		updateHealth();
		updateFoodLevel();
		updateSpeed();
	}
	
	/**
	 * Updates the players data with their client values.
	 * Updates: @health, @foodLevel, @speed
	 * 
	 */
	public void reSync() {
		if(isOnline()) {
			setHealth((getPlayer().getHealth() * getMaxHealth())/20);
			setFoodLevel((getPlayer().getFoodLevel() * getMaxFoodLevel())/20);
			setSpeed(getPlayer().getWalkSpeed());
		}
	}

	/**
	 * Gets the players class.
	 * 
	 * @return The players class.
	 */
	public SNClass getPlayerClass() {
		return playerClass;
	}

	/**
	 * Sets the players class.
	 * 
	 * @param playerClass - The new player class.
	 */
	public void setPlayerClass(SNClass playerClass, boolean force) {
		PlayerChangeClassEvent event = new PlayerChangeClassEvent(getPlayer(), this.playerClass, playerClass);
		
		// Change the target class if modified.
		playerClass = event.getTo();
		
		if(!(event.isCancelled())) {
			this.playerClass = playerClass;
			syncFields(force);
		}
	}
	
	/**
	 * Gets the available classes for the player to join.
	 * 
	 * @return The joinable classes for the players current level.
	 */
	public List<SNClass> getJoinableClasses() {
		return getPlayerClass().getJoinableClasses(getLevel());
	}

	/**
	 * Gets the players available powers for their current level.
	 * 
	 * @return The available powers for their level.
	 */
	public List<Power> getPowers() {		
		return getPlayerClass().getPowers(getLevel());
	}

	/**
	 * Gets the players available abilities for their current level.
	 * 
	 * @return The available abilities for their level.
	 */
	public List<Ability> getAbilities() {
		return getPlayerClass().getAbilities(getLevel());
	}
	
	/**
	 * Gets the players power cooldowns.
	 * 
	 * @return The players power cooldowns.
	 */
	public HashMap<Class<? extends Power>, Long> getCooldowns() {
		return cooldowns;
	}
	
	public void setCooldown(Power power, Long castTime) {
		cooldowns.put(power.getClass(), castTime);
	}
	
	/**
	 * Gets the remaining cooldown for a power.
	 * 
	 * @param power - The power to check.
	 * @return The players remaining cooldown.
	 */
	public Long getRemainingCooldown(Power power) {
		for(Class<? extends Power> c : cooldowns.keySet()) {
			if(c.equals(power.getClass())) {				
				Long remaining = (long) (power.getCooldown() * 1000 + cooldowns.get(c) - System.currentTimeMillis());
				
				return remaining > (long) 0 ? remaining : (long) 0;
			}
		}
		
		return (long) 0;
	}

	/**
	 * Synchronizes the players maximum values with the maximum race values for their level.
	 * 
	 * @param values - Determines whether to update the values as well as the max values.
	 */
	public void syncFields(boolean values) {
		setSpeed(getPlayerClass().getSpeed(getLevel()));
		setMaxMana(getPlayerClass().getMaxMana(getLevel()));
		setMaxHealth(getPlayerClass().getMaxHealth(getLevel()));
		setMaxFoodLevel(getPlayerClass().getMaxFoodLevel(getLevel()));
		
		if(values) {
			setMana(0);
			setHealth(getMaxHealth());
			setFoodLevel(getMaxFoodLevel());
		}	
	}

	/**
	 * Gets the players power binding.
	 * 
	 * @return The players power binding.
	 */
	public Integer getBinding() {
		return binding;
	}

	/**
	 * Sets the players power binding.
	 * 
	 * @param binding - The players new power binding.
	 */
	public void setBinding(Integer binding) {
		this.binding = binding;
	}

	/**
	 * Sets players binding to the id of the next available power.
	 * 
	 */
	public void setNextBinding() {
		if (binding + 1 >= getPowers().size()) {
			binding = 0;
		} else {
			binding++;
		}
	}

	/**
	 * Gets the players selected power.
	 * 
	 * @return The players selected power.
	 */
	public Power getSelectedPower() {
		try {
			return getPowers().get(binding);
		} catch (Exception e) {
			return null;
		}
	}

	// Leveling:
	/**
	 * Gets the players experience.
	 * 
	 * @return The players experience.
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * Sets the players experience.
	 * 
	 * @param experience - The players new experience.
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * Gets the required experience till the next level.
	 * 
	 * @param level - The level.
	 * @return The required experience till the next level.
	 */
	public Integer getExperienceTill(Integer level) {
		return 25 * level * level - 25 * level;
	}

	/**
	 * Gets the players current level based on their experience.
	 * 
	 * @return The players current level.
	 */
	public Integer getLevel() {		
		return new Double((Math.floor(25 + Math.sqrt(625 + 100 * experience)) / 50)).intValue();
	}

	/**
	 * Set the players level.
	 * 
	 * @param level - The new level.
	 */
	public void setLevel(Integer level) {
		setExperience(getExperienceTill(level));
	}

	/**
	 * Get the players total attributes points.
	 * 
	 * @return The players total attribute points.
	 */
	public Integer getAttributePoints() {
		return attributePoints;
	}

	/**
	 * Gets the players remaining attribute points
	 * 
	 * @return The players remaining attribute points.
	 */
	public Integer getRemainingAttributePoints() {
		return getAttributePoints() - strengthStat - resistanceStat - dexterityStat - magicStat;
	}

	/**
	 * Gets the players invested attribute points.
	 * 
	 * @return The players invested attribute points.
	 */
	public Integer getInvestedAttributePoints() {
		return getAttributePoints() - getRemainingAttributePoints();
	}

	/**
	 * Sets the players attribute points
	 * 
	 * @param attributePoints - The players new attribute points.
	 */
	public void setAttributePoints(Integer attributePoints) {
		this.attributePoints = attributePoints;
	}

	/**
	 * Gets the players strength statistic.
	 * 
	 * @return The players strength statistic.
	 */
	public Integer getStrengthStat() {
		return strengthStat;
	}

	/**
	 * Sets the players strength statistic.
	 * 
	 * @param strengthStat - The players new strength statistic.
	 */
	public void setStrengthStat(Integer strengthStat) {
		this.strengthStat = strengthStat;
	}

	/**
	 * Gets the players resistance statistic.
	 * 
	 * @return The players resistance statistic.
	 */
	public Integer getResistanceStat() {
		return resistanceStat;
	}

	/**
	 * Sets the players resistance statistic.
	 * 
	 * @param strengthStat - The players new resistance statistic.
	 */
	public void setResistanceStat(Integer resistanceStat) {
		this.resistanceStat = resistanceStat;
	}

	/**
	 * Gets the players dexterity statistic.
	 * 
	 * @return The players dexterity statistic.
	 */
	public Integer getDexterityStat() {
		return dexterityStat;
	}

	/**
	 * Sets the players dexterity statistic.
	 * 
	 * @param strengthStat - The players new dexterity statistic.
	 */
	public void setDexterityStat(Integer dexterityStat) {
		this.dexterityStat = dexterityStat;
	}

	/**
	 * Gets the players magic statistic.
	 * 
	 * @return The players magic statistic.
	 */
	public Integer getMagicStat() {
		return magicStat;
	}

	/**
	 * Sets the players magic statistic.
	 * 
	 * @param strengthStat - The players new magic statistic.
	 */
	public void setMagicStat(Integer magicStat) {
		this.magicStat = magicStat;
	}

	/**
	 * Gets the SNEntity representation of the player. This is 
	 * used to add or remove entity effects for the player.
	 * 
	 * @return The SNEntity of the player.
	 */
	public SNEntity getEntity() {
		return SNEntities.i.get(getPlayer());
	}
	
	// Bukkit:
	public Player getPlayer() {
		return Bukkit.getPlayerExact(name);
	}

	public Boolean isOnline() {
		return getPlayer() != null;
	}	

	public Boolean isOffline() {
		return !(isOnline());
	}

	public void sendMessage(String message) {
		if (isOnline()) {
			getPlayer().sendMessage(message);
		}
	}

	public void sendMessage(String[] messages) {
		for (String message : messages) {
			sendMessage(message);
		}
	}

	// Java:
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SNPlayer other = (SNPlayer) obj;
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

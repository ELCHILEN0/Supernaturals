package com.TeamNovus.Supernaturals.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BlockIterator;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Columns.Column;
import com.TeamNovus.Persistence.Annotations.Columns.Id;
import com.TeamNovus.Supernaturals.SNClasses;
import com.TeamNovus.Supernaturals.Database.Database;
import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTriggerEvent;
import com.TeamNovus.Supernaturals.Events.PlayerClassChangeEvent;
import com.TeamNovus.Supernaturals.Events.PlayerClassChangeEvent.ChangeClassCause;
import com.TeamNovus.Supernaturals.Events.PlayerLevelUpEvent;
import com.TeamNovus.Supernaturals.Events.PlayerManaChangeEvent;
import com.TeamNovus.Supernaturals.Player.Class.Ability;
import com.TeamNovus.Supernaturals.Player.Class.Power;
import com.TeamNovus.Supernaturals.Player.Statistics.Cooldown;

import static com.TeamNovus.Persistence.Queries.Expression.Expressions.*;

@Table(name = "sn_players")
public class SNPlayer {			
	public static final String ID 				= "id";
	public static final String NAME 			= "name";
	public static final String MANA 			= "mana";
	public static final String MAX_MANA 		= "max_mana";
	public static final String FOOD_LEVEL 		= "food_level";
	public static final String MAX_FOOD_LEVEL 	= "max_food_level";
	public static final String PLAYER_CLASS 	= "player_class";
	public static final String POWER_BINDING 	= "power_binding";
	public static final String EXPERIENCE 		= "experience";
	public static final String KILLS 			= "kills";
	public static final String DEATHS 			= "deaths";
	public static final String VERBOSE			= "verbose";
	public static final String GUI	 			= "gui";
	
	@Id
	@Column(name = ID)
	private Integer id;
	
	@Column(name = NAME)
	private String name;

	// Data Values:
	@Column(name = MANA)
	private Integer mana;
	
	@Column(name = MAX_MANA)
	private Integer maxMana;
	
	@Column(name = FOOD_LEVEL)
	private Integer foodLevel;
	
	@Column(name = MAX_FOOD_LEVEL)
	private Integer maxFoodLevel;

	// Class:
	@Column(name = PLAYER_CLASS)
	private String playerClass;
	
	@Column(name = POWER_BINDING)
	private Integer powerBinding;
	
	// Leveling:
	@Column(name = EXPERIENCE)
	private Integer experience;
	
	private Integer quest;
	
	private Integer questProgress;
	
	private Integer attributePoints;

	// Stats:
	@Column(name = KILLS)
	private Integer kills;
	
	@Column(name = DEATHS)
	private Integer deaths;
	
	// Attributes:
	private Integer healthAttribute;
	private Integer healthRegenAttribute;
	private Integer manaAttribute;
	private Integer manaRegenAttribute;
	private Integer speedAttribute;
	private Integer attackAttribute;
	private Integer defenseAttribute;
	
	@Column(name = VERBOSE)
	private Boolean verbose;
	
	@Column(name = GUI)
	private Boolean gui;
	
	public static SNPlayer getPlayer(int id) {
		return Database.find(SNPlayer.class, id);
	}
	
	public static SNPlayer getPlayer(String name) {
		return Database.select(SNPlayer.class).where(equal(NAME, name)).findOne();
	}
	
	public static SNPlayer getPlayer(Player player) {
		SNPlayer p = getPlayer(player.getName());
		
		if(p == null) {
			p = new SNPlayer(player);
			p.save();
		}
		
		return p;
	}
	
	public static List<SNPlayer> getAllPlayers() {
		return Database.findAll(SNPlayer.class);
	}
	
	public static List<SNPlayer> getOnlinePlayers() {
		ArrayList<SNPlayer> players = new ArrayList<SNPlayer>();
				
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			players.add(getPlayer(p));
		}
				
		return players;
	}
	
	public static List<SNPlayer> getPlayersInClass(SNClass playerClass) {
		return Database.select(SNPlayer.class).where(equal(PLAYER_CLASS, playerClass.getName())).findList();
	}
	
	// Empty constructor for reflection.
	public SNPlayer() {		
		// Data Values:
		this.mana = 0;
		this.foodLevel = 20;

		this.maxMana = 0;
		this.maxFoodLevel = 20;

		// Class:
		this.playerClass = SNClasses.getBaseClass().getName();
		this.powerBinding = 0;

		// Leveling:
		this.experience = 0;
		this.attributePoints = 0;

		// Stats:
		this.kills = 0;
		this.deaths = 0;
		
		// Attributes:
		this.healthAttribute = 0;
		this.healthRegenAttribute = 0;
		this.manaAttribute = 0;
		this.manaRegenAttribute = 0;
		this.speedAttribute = 0;
		this.attackAttribute = 0;
		this.defenseAttribute = 0;			
		
		this.verbose = true;
		this.gui = true;		
	}
	
	public SNPlayer(Player p) { 
		this();
		this.name = p.getName();
	}
	
	public boolean save() {
		return Database.save(this);
	}
	
	/**
	 * Get the players id
	 * 
	 */
	public Integer getId() {
		if(id == null) {
			save();
		}
		
		return id;
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
	
	public void setMana(Integer mana) {
		setMana(mana, false);
	}

	/**
	 * Sets the players current mana.
	 * 
	 * @param mana - The players new mana.
	 */
	public void setMana(Integer mana, boolean fire) {	
		if(fire) {
			PlayerManaChangeEvent event = new PlayerManaChangeEvent(getPlayer(), mana - getMana());
			
			Bukkit.getPluginManager().callEvent(event);
			
			mana = getMana() + event.getAmount();
			
			if(event.isCancelled())
				return;
		}
		
		this.mana = mana;
		
		updateGUI();
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
	public double getHealth() {
		return getPlayer().getHealth();
	}

	/**
	 * Sets the players health.
	 * 
	 * @param health - The players new health.
	 */
	public void setHealth(double health) {
		if(health > getMaxHealth())
			health = getMaxHealth();

		if(health < 0)
			health = 0;
		
		getPlayer().setHealth(health);
		getPlayer().setHealthScaled(false);
	}

	/**
	 * Gets the players maximum health.
	 * 
	 * @return The players maximum health.
	 */
	public double getMaxHealth() {
		return getPlayer().getMaxHealth();
	}

	/**
	 * Sets the players maximum health.
	 * 
	 * @param maxHealth - The players new maximum health.
	 */
	public void setMaxHealth(Integer maxHealth) {
		if(maxHealth <= 0)
			maxHealth = 20;
		
		getPlayer().setMaxHealth(maxHealth);
	}

	/**
	 * Gets the players food level.
	 * 
	 * @return The players food level.
	 */
	public Integer getFoodLevel() {
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
	 * @param maxFoodLevel - The players new maximum food level.
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
			getPlayer().setFoodLevel(foodLevel * 20 / maxFoodLevel);
	}

	/**
	 * Gets the players walking speed.
	 * 
	 * @return The players speed.
	 */
	public Float getSpeed() {		
		return getPlayer().getWalkSpeed();
	}

	/**
	 * Sets the players walking speed.
	 * 
	 * @param speed - The players new walking speed.
	 */
	public void setSpeed(Float speed) {		
		getPlayer().setWalkSpeed(speed);
	}

	/**
	 * Gets the players class.
	 * 
	 * @return The players class.
	 */
	public SNClass getPlayerClass() {
		return SNClasses.getExactClass(playerClass);
	}

	public void setPlayerClass(String playerClassName) {
		this.playerClass = playerClassName;
	}
	
	/**
	 * Sets the players class.
	 * 
	 * @param playerClassName - The new player class.
	 * @param fire - Call the PlayerClassChangeEvent.
	 */
	public void setPlayerClass(SNClass playerClass, boolean fire) {
		if(fire) {
			PlayerClassChangeEvent event = new PlayerClassChangeEvent(getPlayer(), ChangeClassCause.CODE, getPlayerClass(), playerClass);
			
			Bukkit.getPluginManager().callEvent(event);
			
			// Change the target class if modified.
			this.playerClass = event.getTo().getName();
			
			if(event.isCancelled())
				return;
		}

		this.playerClass = playerClass.getName();
		
		syncFields(fire);
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
	public List<Cooldown> getCooldowns() {		
		return Database.select(Cooldown.class).where(equal(Cooldown.PLAYER_ID, getId())).findList();
	}
	
	public void setCooldown(Cooldown cooldown) {	
		cooldown.setPlayerId(getId());
		cooldown.save();
	}
	
	/**
	 * Gets the remaining cooldown for a power.
	 * 
	 * @param power - The power to check.
	 * @return The players remaining cooldown.
	 */
	public int getRemainingCooldownTime(Power power) {
		for(Cooldown cooldown : getCooldowns()) {
			if(cooldown.getPower().equals(power.getName())) {	
				return cooldown.getRemaining();
			}
		}
		
		return 0;
	}

	/**
	 * Synchronizes the players maximum values with the maximum race values for their level.
	 * 
	 * @param heal - Determines whether to set the players values to their max values.
	 */
	public void syncFields(boolean heal) {
		setSpeed(getPlayerClass().getSpeed(getLevel()));
		setMaxMana(getPlayerClass().getMaxMana(getLevel()));
		setMaxHealth(getPlayerClass().getMaxHealth(getLevel()));
		setMaxFoodLevel(getPlayerClass().getMaxFoodLevel(getLevel()));
		
		
		if(heal) {
			setMana(getMaxMana(), false);
			setHealth(getMaxHealth());
			setFoodLevel(getMaxFoodLevel());
		} else {
			setMana(getMana());
			setHealth(getHealth());
			setFoodLevel(getFoodLevel());
		}		
	}

	/**
	 * Gets the players power binding.
	 * 
	 * @return The players power binding.
	 */
	public Integer getPowerBinding() {
		return powerBinding;
	}

	/**
	 * Sets the players power binding.
	 * 
	 * @param binding - The players new power binding.
	 */
	public void setPowerBinding(Integer powerBinding) {
		this.powerBinding = powerBinding;
	}

	/**
	 * Sets players binding to the id of the next available power.
	 * 
	 */
	public void setNextPowerBinding() {
		if (powerBinding + 1 >= getPowers().size()) {
			powerBinding = 0;
		} else {
			powerBinding++;
		}
	}

	/**
	 * Gets the players selected power.
	 * 
	 * @return The players selected power.
	 */
	public Power getSelectedPower() {
		try {
			return getPowers().get(powerBinding);
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
		int lastLevel = getLevel();
		
		this.experience = experience;
		save();
		syncFields(false);

		if(getLevel() > lastLevel && isOnline()) {
			PlayerLevelUpEvent event = new PlayerLevelUpEvent(getPlayer());
			
			Bukkit.getPluginManager().callEvent(event);
		}	
		
		updateGUI();
	}
	
	/**
	 * Gets the required experience for @level.
	 * 
	 * @param level - The level to get the required experience for.
	 * @return The required experience for @level.
	 */
	public Integer getExperienceFor(Integer level) {
		return 25 * level * level - 25 * level;
	}
	
	/**
	 * Gets the total required experience for @level.
	 * 
	 * @param level - The level to get the total required experience for.
	 * @return The total required experience for @level.
	 */
	public Integer getTotalExperienceFor(Integer level) {
		int exp = 0;
		
		for (int i = 0; i <= level; i++) {
			exp += 25 * i * i - 25 * i;
		}
		
		return exp;
	}

	/**
	 * Gets the players current level based on their experience.
	 * 
	 * @return The players current level.
	 */
	public Integer getLevel() {
		int level = 1;
		
		while(experience - getTotalExperienceFor(level) > 0) {
			level++;
		}
		
		return level;		
	}

	/**
	 * Set the players level.
	 * 
	 * @param level - The new level.
	 */
	public void setLevel(Integer level) {
		setExperience(getTotalExperienceFor(level));
	}
	
	/**
	 * Get the total number of kills.
	 * 
	 * @return - The total number of kills.
	 */
	public Integer getKills() {
		return kills;
	}
	
	/**
	 * Set the total number of kills.
	 * 
	 * @param kills - The new number of kills.
	 */
	public void setKills(Integer kills) {
		this.kills = kills;
	}
	
	public double getKD() {
		if(kills == 0) {
			return -deaths;
		} else if(deaths == 0) {
			return kills;
		} else {
			return 1.0 * kills / deaths;
		}
	}
	
	/**
	 * Get the total number of player deaths.
	 * 
	 * @return - The total number of player deaths.
	 */
	public Integer getDeaths() {
		return deaths;
	}
	
	/**
	 * Set the total number of player deaths.
	 * 
	 * @param deaths - The total number of player deaths.
	 */
	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	/**
	 * Get the players available attributes points.
	 * 
	 * @return The players available attribute points.
	 */
	public Integer getAttributePoints() {
		return attributePoints;
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
	 * Gets the players health attribute.
	 * 
	 * @return The players health attribute.
	 */
	public Integer getHealthAttribute() {
		return healthAttribute;
	}
	
	/**
	 * Sets the players health attribute.
	 * 
	 * @param healthAttribute - The new health attribute
	 */
	public void setHealthAttribute(Integer healthAttribute) {
		this.healthAttribute = healthAttribute;
	}
	
	/**
	 * Gets the players health regen attribute.
	 * 
	 * @return The players health regen attribute.
	 */
	public Integer getHealthRegenAttribute() {
		return healthRegenAttribute;
	}
	
	/**
	 * Sets the players health regen attribute.
	 * 
	 * @param healthRegenAttribute - The new health regen attribute
	 */
	public void setHealthRegenAttribute(Integer healthRegenAttribute) {
		this.healthRegenAttribute = healthRegenAttribute;
	}
	
	/**
	 * Gets the players mana attribute.
	 * 
	 * @return The players mana attribute.
	 */
	public Integer getManaAttribute() {
		return manaAttribute;
	}
	
	/**
	 * Sets the players mana attribute.
	 * 
	 * @param manaAttribute - The new mana attribute
	 */
	public void setManaAttribute(Integer manaAttribute) {
		this.manaAttribute = manaAttribute;
	}
	
	/**
	 * Gets the players mana regen attribute.
	 * 
	 * @return The players mana regen attribute.
	 */
	public Integer getManaRegenAttribute() {
		return manaRegenAttribute;
	}
	
	/**
	 * Sets the players mana regen attribute.
	 * 
	 * @param manaRegenAttribute - The new mana regen attribute
	 */
	public void setManaRegenAttribute(Integer manaRegenAttribute) {
		this.manaRegenAttribute = manaRegenAttribute;
	}
	
	/**
	 * Gets the players speed attribute.
	 * 
	 * @return The players speed attribute.
	 */
	public Integer getSpeedAttribute() {
		return speedAttribute;
	}
	
	/**
	 * Sets the players speed attribute.
	 * 
	 * @param speedAttribute - The new speed attribute
	 */
	public void setSpeedAttribute(Integer speedAttribute) {
		this.speedAttribute = speedAttribute;
	}
	
	/**
	 * Gets the players attack attribute.
	 * 
	 * @return The players attack attribute.
	 */
	public Integer getAttackAttribute() {
		return attackAttribute;
	}
	
	/**
	 * Sets the players attack attribute.
	 * 
	 * @param attackAttribute - The new attack attribute
	 */
	public void setAttackAttribute(Integer attackAttribute) {
		this.attackAttribute = attackAttribute;
	}
	
	/**
	 * Gets the players defense attribute.
	 * 
	 * @return The players defense attribute.
	 */
	public Integer getDefenseAttribute() {
		return defenseAttribute;
	}
	
	/**
	 * Sets the players defense attribute.
	 * 
	 * @param defenseAttribute - The new defense attribute
	 */
	public void setDefenseAttribute(Integer defenseAttribute) {
		this.defenseAttribute = defenseAttribute;
	}
	
	/**
	 * Returns true if the player has verbose mode enabled.
	 * 
	 * @return True if verbose is true, false otherwise.
	 */
	public Boolean isVerbose() {
		return verbose;
	}
	
	/**
	 * Sets the players verbose mode.
	 * 
	 * @param verbose - The players new verbose mode.
	 */
	public void setVerbose(Boolean verbose) {
		this.verbose = verbose;
	}

	/**
	 * Returns true if the player has gui enabled.
	 * 
	 * @return True if gui is true, false otherwise.
	 */
	public Boolean isUsingGUI() {
		return gui;
	}
	
	/**
	 * Sets the players gui toggle.
	 * 
	 * @param verbose - The players new gui toggle.
	 */
	public void setUsingGUI(Boolean gui) {
		this.gui = gui;
	}
	
	public void updateGUI() {
		if(isOffline())
			return;
		
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		if(isUsingGUI()) {			
			if(scoreboard.getObjective("info") == null)
				scoreboard.registerNewObjective("info", "dummy");
			
			Objective info = scoreboard.getObjective("info");
			info.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			info.setDisplayName(getPlayerClass().getColor() + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE  + getPlayerClass().getName() + ChatColor.RESET + ChatColor.RED + ChatColor.ITALIC + " " + getLevel() + "/" + getPlayerClass().getMaxLevel());
			
			Score mana = info.getScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Mana:"));
			Score exp = info.getScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Exp:"));
			
			mana.setScore((int) ((getMana() * 100.0)/getMaxMana()));
			
			double currentExp = getExperience() - getTotalExperienceFor(getLevel() - 1);
			double maxExp = getTotalExperienceFor(getLevel()) - getTotalExperienceFor(getLevel() - 1);
			exp.setScore((int) ((currentExp * 100.0)/maxExp));
		}
		
		if(scoreboard.getObjective("health") == null)
			scoreboard.registerNewObjective("health", "dummy");
		
		Objective health = scoreboard.getObjective("health");
		health.setDisplaySlot(DisplaySlot.BELOW_NAME);
		
		health.setDisplayName("health");
		 
		for(Player player : Bukkit.getOnlinePlayers()){
			health.getScore(player).setScore((int) player.getHealth());
		}	
		
		getPlayer().setScoreboard(scoreboard);
	}
	
	/**
	 * Gets the SNEntity representation of the player. This is 
	 * used to add or remove entity effects for the player.
	 * 
	 * @return The SNEntity of the player.
	 */
	public SNEntity getEntity() {
		return SNEntity.getEntity(getPlayer());
	}
	
	public void tick() {
		if(!(isOnline())) return;
		
		Iterator<Ability> abilityIterator = getAbilities().iterator();
		while (abilityIterator.hasNext()) {
			Ability ability = abilityIterator.next();
			
			Bukkit.getPluginManager().callEvent(new EntityEffectTickEvent(getPlayer(), ability));
			
			if (ability.isPeriodic()) {				
				if (ability.getElapsed() % ability.getPeriod() == 0) {
					Bukkit.getPluginManager().callEvent(new EntityEffectTriggerEvent(getPlayer(), ability));
					
					// Reset elapsed to ensure least amount of integer usage.
					ability.setElapsed(0);
				}
			}
			
			ability.setElapsed(ability.getElapsed() + 1);
		}
	}
	
	public LivingEntity getTargetEntity(Integer range) {
		if(isOffline()) return null;
		
		// Get the nearby entities
		List<org.bukkit.entity.Entity> near = getPlayer().getNearbyEntities(range, range, range);
		List<LivingEntity> entities = new ArrayList<LivingEntity>(); 
		for (org.bukkit.entity.Entity e : near) {
			if (e instanceof LivingEntity) {
				entities.add((LivingEntity) e);
			}
		}

		// Find the target
		LivingEntity target = null;		
		BlockIterator iterator = new BlockIterator(getPlayer(), range);

		Block b;
		Location l;
		int bx, by, bz;
		double ex, ey, ez;
		// Loop through the players line of sight
		while (iterator.hasNext()) {
			b = iterator.next();
			bx = b.getX();
			by = b.getY();
			bz = b.getZ();

			// Check each entity in the range to see if its near the line of sight
			for (LivingEntity e : entities) {
				l = e.getLocation();
				ex = l.getX();
				ey = l.getY();
				ez = l.getZ();

				// Check if the entity is near the line of sight
				if ((bx-.75 <= ex && ex <= bx+1.75) && (bz-.75 <= ez && ez <= bz+1.75) && (by-1 <= ey && ey <= by+2.5)) {
					target = e;
					return target;
				}
			}
		}

		return null;
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
	public String toString() {
		return "SNPlayer [id=" + id + ", name=" + name + ", mana=" + mana
				+ ", maxMana=" + maxMana + ", foodLevel=" + foodLevel
				+ ", maxFoodLevel=" + maxFoodLevel + ", playerClass="
				+ playerClass + ", powerBinding=" + powerBinding
				+ ", experience=" + experience + ", quest=" + quest
				+ ", questProgress=" + questProgress + ", attributePoints="
				+ attributePoints + ", kills=" + kills + ", deaths=" + deaths
				+ ", healthAttribute=" + healthAttribute
				+ ", healthRegenAttribute=" + healthRegenAttribute
				+ ", manaAttribute=" + manaAttribute + ", manaRegenAttribute="
				+ manaRegenAttribute + ", speedAttribute=" + speedAttribute
				+ ", attackAttribute=" + attackAttribute
				+ ", defenseAttribute=" + defenseAttribute + ", verbose="
				+ verbose + ", gui=" + gui + "]";
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

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

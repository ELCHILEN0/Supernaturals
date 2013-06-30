package com.TeamNovus.Supernaturals.Models;

import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Reagent {
	private Integer exp;
	private Integer health;
	private Integer hunger;
	private Integer mana;
	private ItemBag items;
	
	public Reagent() {
		this.exp = 0;
		this.health = 0;
		this.hunger = 0;
		this.mana = 0;
		this.items = new ItemBag();
	}
	
	public Reagent(Integer mana) {
		this();
		this.mana = mana;
	}
	
	public Reagent(ItemBag items) {
		this();
		this.items = items;
	}
	
	public Reagent(Integer exp, Integer health, Integer hunger, Integer mana, ItemBag items) {
		this();
		this.exp = exp;
		this.health = health;
		this.hunger = hunger;
		this.mana = mana;
		this.items = items;
	}

	public Reagent(Reagent copy) {
		this(copy.exp, copy.health, copy.hunger, copy.mana, copy.items);
	}
	
	/**
	 * Check if a player has all the required reagents.
	 * 
	 * @param player - The player to check
	 * @return - Returns true if the player has all the required reagents.
	 */
	public boolean has(SNPlayer player) {
		return player.getPlayer().getExp() >= exp &&
					player.getHealth() > health &&
					player.getFoodLevel() >= hunger &&
					player.getMana() >= mana &&
					items.has(player.getPlayer());
	}
	
	/**
	 * Consume the specified reagents.
	 * 
	 * @param player - The player to consume the reagents.
	 */
	public void consume(SNPlayer player) {		
		player.getPlayer().setExp(player.getPlayer().getExp() - exp);
		
		// Works with @health and @foodLevel.
		player.setHealth(player.getHealth() - health);
		player.setFoodLevel(player.getFoodLevel() - hunger);
		player.setMana(player.getMana() - mana, false);
		
		items.consume(player.getPlayer());
	}
	
	public Integer getExpCost() {
		return exp;
	}
	
	public void setExpCost(Integer exp) {
		this.exp = exp;
	}
	
	public Integer getHealthCost() {
		return health;
	}
	
	public void setHealthCost(Integer health) {
		this.health = health;
	}
	
	public Integer getHungerCost() {
		return hunger;
	}
	
	public void setHungerCost(Integer hunger) {
		this.hunger = hunger;
	}

	public Integer getManaCost() {
		return mana;
	}
	
	public void setManaCost(Integer mana) {
		this.mana = mana;
	}
	
	public ItemBag getItemBagCost() {
		return items;
	}
	
	public void setItemBagCost(ItemBag items) {
		this.items = items;
	}
	
	public String toString() {
		return String.format("Exp %s, Health %s, Hunger %s, Items %s, Power %s", exp, health, hunger, items, mana);
	}
	
}

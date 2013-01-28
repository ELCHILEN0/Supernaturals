package com.TeamNovus.Supernaturals.Models;

import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Reagent {
	private Integer exp;
	private Integer health;
	private Integer hunger;
	private Integer mana;
	private ItemBag items;
	
	public Reagent(Integer exp, Integer health, Integer hunger, Integer mana, ItemBag items) {
		this.exp = exp;
		this.health = health;
		this.hunger = hunger;
		this.mana = mana;
		this.items = items;
	}
	
	public Reagent() {
		this(0, 0, 0, 0, new ItemBag());
	}
	
	public Reagent(ItemBag items) {
		this(0, 0, 0, 0, items);
	}
	
	public Reagent(Integer mana, ItemBag items) {
		this(0, 0, 0, mana, items);
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
		player.setMana(player.getMana() - mana);
		
		items.consume(player.getPlayer());
	}
	
	public Integer getExpCost() {
		return exp;
	}
	
	public Integer getHealthCost() {
		return health;
	}
	
	public Integer getHungerCost() {
		return hunger;
	}
	
	public Integer getManaCost() {
		return mana;
	}
	
	public ItemBag getItemBagCost() {
		return items;
	}
	
	public String toString() {
		return String.format("Exp %s, Health %s, Hunger %s, Items %s, Power %s", exp, health, hunger, items, mana);
	}
	
}

package com.TeamNovus.SupernaturalRaces.Util;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

public class Reagent {
	// TODO: Money
	private Double money;
	private Integer exp;
	private Integer health;
	private Integer hunger;
	private Integer power;
	private ItemBag items;
	
	public Reagent(Double money, Integer exp, Integer health, Integer hunger, Integer power, ItemBag items) {
		this.money = money;
		this.exp = exp;
		this.health = health;
		this.hunger = hunger;
		this.power = power;
		this.items = items;
	}

	/**
	 * Check if a player meets the reagent requirements
	 * @param player - The player to check
	 * @return - Boolean - If the player has the reagents
	 */
	public boolean has(Player player) {
		SNPlayer snp = SupernaturalRaces.getPlayerManager().getPlayer(player);
		if(player.getExp() >= exp && player.getHealth() > health && player.getFoodLevel() >= hunger && items.has(player) && snp.getPower() >= power) {
			return true;
		}
		return false;
	}
	
	/**
	 * Consume the specified reagents.
	 * Be sure to check if the player has the reagents before attempting
	 * @param player - The player with to consume
	 */
	public void consume(Player player) {
		SNPlayer snp = SupernaturalRaces.getPlayerManager().getPlayer(player);
		player.setExp(player.getExp() - exp);
		player.setHealth(player.getHealth() - health);
		player.setFoodLevel(player.getFoodLevel() - hunger);
		items.consume(player);
		snp.setPower(snp.getPower() - power);
	}
	
	public Double getMoneyCost() {
		return money;
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
	
	public Integer getPowerCost() {
		return power;
	}
	
	public ItemBag getItemBagCost() {
		return items;
	}
	
	public String toString() {
		return String.format("Money %s, Exp %s, Health %s, Hunger %s, Items %s, Power %s", money, exp, health, hunger, items, power);
	}
	
}

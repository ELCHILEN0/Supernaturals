package com.TeamNovus.SupernaturalRaces.Util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

public class Reagent {
	// TODO: Money
	private Double money;
	private Integer exp;
	private Integer health;
	private Integer hunger;
	private ItemStack items;
	private Integer power;
	
	public Reagent(Double money, Integer exp, Integer health, Integer hunger, ItemStack items, Integer power) {
		this.money = money;
		this.exp = exp;
		this.health = health;
		this.hunger = hunger;
		this.items = items;
		this.power = power;
	}

	/**
	 * Check if a player meets the reagent requirements
	 * @param player - The player to check
	 * @return - Boolean - If the player has the reagents
	 */
	public boolean has(Player player) {
		SNPlayer snp = SupernaturalRaces.getPlayerManager().getPlayer(player);
		if(player.getExp() >= exp && player.getHealth() > health && player.getFoodLevel() >= hunger && player.getInventory().contains(items) && snp.getPower() >= power) {
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
		player.getInventory().remove(items);
		snp.setPower(snp.getPower() - power);
	}
	
	public String toString() {
		return String.format("Money %s, Exp %s, Health %s, Hunger %s, Items %s, Power %s", money, exp, health, hunger, items, power);
	}
	
}

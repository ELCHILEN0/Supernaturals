package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;
import com.TeamNovus.SupernaturalRaces.Races.AngelRace;
import com.TeamNovus.SupernaturalRaces.Races.HumanRace;

public class RaceManager {
	private SupernaturalRaces plugin;
	private List<SNRace> races = new ArrayList<SNRace>();

	public RaceManager(SupernaturalRaces plugin) {
		this.plugin = plugin;
	}

	/**
	 * Register any races other than the default race.
	 * The Human races is returned for players without a race
	 * by default.
	 */
	public void registerRaces() {
		races.add(new AngelRace());
	}

	public List<SNRace> getRaces() {
		return races;
	}

	/**
	 * Set a players race
	 */
	public void setRace(SNPlayer player, SNRace race) {
		player.setRace(race.name());
	}

	/**
	 * Get the race a player is currently in
	 */
	public SNRace getRace(SNPlayer player) {
		for(SNRace race : races) {
			if(race.name().equalsIgnoreCase(player.getRace())) {
				return race;
			}
		}
		return new AngelRace();
	}

	private SNPlayer getPlayer(PlayerEvent event) {
		return plugin.getPlayerManager().getPlayer(event.getPlayer());
	}

	private SNRace getRace(PlayerEvent event) {
		return plugin.getRaceManager().getRace(plugin.getPlayerManager().getPlayer(event.getPlayer()));
	}

	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		SNRace race = getRace(event);
		SNPlayer player = getPlayer(event);
		for(SNSpell spell : race.spells()) {
			if(spell.actions().contains(event.getAction())) {
				if(event.getItem() != null && spell.bindings().contains(event.getItem().getType())) {					
					if(spell.power() > player.getPower()) {
						event.getPlayer().sendMessage(ChatColor.RED + "" + spell.power() + " power is needed to perform this spell!");
						return;
					}

					if(spell.execute(event.getPlayer())) {
						player.setPower(player.getPower() - spell.power());
						event.getPlayer().sendMessage(ChatColor.YELLOW + "New Power: " + player.getPower() + "/" + race.maxPower());
	
						if(spell.consume()) {
							ItemStack consumed = event.getItem();
							consumed.setAmount(event.getItem().getAmount()-1);
							event.getPlayer().setItemInHand(consumed);
							event.getPlayer().sendMessage(ChatColor.BLUE + "Your item has been consumed!");
						}
					}
				}
			}
		}
	}
	
	public void onPlayerDamageEvent(PlayerDamageEvent event) {
		System.out.println("[RaceManager] Player Damage Event Called!");
	}
	
	public void onPlayerDamageEntityEvent(PlayerDamageEntityEvent event) {
		System.out.println("[RaceManager] Player Damage Entity Event Called!");
	}
	
}
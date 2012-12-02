package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.SNRace;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Character.SNSpell;
import com.TeamNovus.SupernaturalRaces.Events.ServerTickEvent;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;

/**
 * This class is designed to listen to and trigger any custom listeners
 *
 */
public class CustomListener implements Listener {
	
	@EventHandler
	public void onServerTick(ServerTickEvent event) {
		SupernaturalRaces.getEntityManager().tickAll();
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		// Bind all Spells to the BLAZE_ROD
		if(!(event.getPlayer().getItemInHand().getData().getItemType().equals(Material.BLAZE_ROD)))
			return;

		// Bind/Switch:
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) ||
				event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(event.getPlayer());
			SNRace race = SupernaturalRaces.getRaceManager().getRace(player);

			if(player.getBoundSpellId() + 1 >= race.spells().size()) {
				player.setBoundSpellId(0);
			} else {
				player.setBoundSpellId(player.getBoundSpellId() + 1);
			}				

			if(race.spells().size() != 0) {
				event.getPlayer().sendMessage(ChatColor.GREEN + "Wand bound to " + ChatColor.YELLOW + race.spells().get(player.getBoundSpellId()).name() + ChatColor.GREEN + "!");
			}
		}


		// Cast:
		if(event.getAction().equals(Action.LEFT_CLICK_AIR)) {
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(event.getPlayer());
			SNRace race = SupernaturalRaces.getRaceManager().getRace(player);

			if(race.spells().size() != 0 && player.getBoundSpellId() < race.spells().size()) {
				SNSpell spell = race.spells().get(player.getBoundSpellId());

				if(spell.required().has(event.getPlayer())) {
					if(spell.execute(event.getPlayer())) {
						spell.consume().consume(event.getPlayer());
					}
				} else {
					event.getPlayer().sendMessage(ChatColor.BLUE + "Requires:");
					if(spell.required().getMoneyCost() != 0)
						event.getPlayer().sendMessage(ChatColor.BLUE + "   Money: " + ChatColor.YELLOW + spell.required().getMoneyCost());
					if(spell.required().getExpCost() != 0)
						event.getPlayer().sendMessage(ChatColor.BLUE + "   Experience: " + ChatColor.YELLOW + spell.required().getExpCost());
					if(spell.required().getHealthCost() != 0)
						event.getPlayer().sendMessage(ChatColor.BLUE + "   Health: " + ChatColor.YELLOW + spell.required().getHealthCost());
					if(spell.required().getHungerCost() != 0)
						event.getPlayer().sendMessage(ChatColor.BLUE + "   Hunger: " + ChatColor.YELLOW + spell.required().getHungerCost());
					if(spell.required().getPowerCost() != 0)
						event.getPlayer().sendMessage(ChatColor.BLUE + "   Power: " + ChatColor.YELLOW + spell.required().getPowerCost());
					if(spell.required().getItemBagCost() != new ItemBag())
						event.getPlayer().sendMessage(ChatColor.BLUE + "   Items: " + ChatColor.YELLOW + spell.required().getItemBagCost().toString());
				}
			} else {
				event.getPlayer().sendMessage(ChatColor.RED + "Your wand is not bound to a spell!");
			}
		}
	}
}

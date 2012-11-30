package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageByEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityByProjectileEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerKillEntityEvent;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;

/**
 * This class is designed to listen to and trigger any custom listeners
 *
 */
public class CustomListener implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			PlayerDamageEvent newEvent = new PlayerDamageEvent(
					(Player) event.getEntity(),
					event.getCause(),
					event.getDamage());

			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);

			event.setDamage(newEvent.getDamage());

			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}		
		}
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {	
		if(event.getDamager() instanceof Player) {
			PlayerDamageEntityEvent newEvent = new PlayerDamageEntityEvent(
					(Player) event.getDamager(),
					event.getEntity(),
					event.getCause(),
					event.getDamage());

			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);

			event.setDamage(newEvent.getDamage());

			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}
		} else if(event.getDamager() instanceof Projectile && ((Projectile) event.getDamager()).getShooter() instanceof Player) {
			PlayerDamageEntityByProjectileEvent newEvent = new PlayerDamageEntityByProjectileEvent(
					(Player) ((Projectile) event.getDamager()).getShooter(),
					event.getEntity(),
					event.getDamage(),
					((Projectile) event.getDamager()));

			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);

			event.setDamage(newEvent.getDamage());

			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}
		}

		if(event.getEntity() instanceof Player) {
			PlayerDamageByEntityEvent newEvent = new PlayerDamageByEntityEvent(
					(Player) event.getEntity(),
					event.getEntity(),
					event.getCause(),
					event.getDamage());

			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);

			event.setDamage(newEvent.getDamage());

			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerKillEntity(EntityDeathEvent event) {
		if(event.getEntity().getKiller() instanceof Player) {
			PlayerKillEntityEvent newEvent = new PlayerKillEntityEvent(
					event.getEntity().getKiller(), 
					event.getEntity(), 
					event.getDroppedExp(), 
					event.getDrops());
			
			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);

			event.setDroppedExp(event.getDroppedExp());
		}
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
			Race race = SupernaturalRaces.getRaceManager().getRace(player);

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
			Race race = SupernaturalRaces.getRaceManager().getRace(player);

			if(race.spells().size() != 0 && player.getBoundSpellId() < race.spells().size()) {
				Spell spell = race.spells().get(player.getBoundSpellId());

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

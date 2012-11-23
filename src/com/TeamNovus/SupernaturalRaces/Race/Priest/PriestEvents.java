package com.TeamNovus.SupernaturalRaces.Race.Priest;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerKillEntityEvent;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Race.Demon.DemonRace;
import com.TeamNovus.SupernaturalRaces.Race.Human.HumanRace;
import com.TeamNovus.SupernaturalRaces.Race.Necromancer.NecromancerRace;
import com.TeamNovus.SupernaturalRaces.Race.Vampire.VampireRace;
import com.TeamNovus.SupernaturalRaces.Race.Werewolf.WerewolfRace;

public class PriestEvents implements SNEventListener {
	
	@SNEventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		event.setDamage((int) Math.floor(event.getDamage() * 0.8));
	}
	
	@SNEventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Race race = SupernaturalRaces.getPlayerManager().getPlayer((Player) event.getEntity()).getPlayerRace();
			if(race instanceof DemonRace || race instanceof VampireRace || race instanceof WerewolfRace || race instanceof NecromancerRace) {
				event.setDamage((int) Math.ceil(event.getDamage() * 1.25));
			}
		}
	}
	
	@SNEventHandler
	public void onPlayerKillEntity(PlayerKillEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player killed = (Player) event.getEntity();
			SNPlayer k = SupernaturalRaces.getPlayerManager().getPlayer(killed);
			if(k.getPlayerRace() instanceof WerewolfRace || k.getPlayerRace() instanceof VampireRace) {
				if((Math.random() * 100) < 25) {
					k.setRace(new HumanRace());
					killed.sendMessage(ChatColor.RED + "You are cured of your sins!  You become a Human!");
					event.getPlayer().sendMessage(ChatColor.GREEN + "Your holy powers have caused " + ChatColor.YELLOW + killed.getName() + ChatColor.GREEN + "to become a Human!");
					return;
				}
			}
		}
	}
}

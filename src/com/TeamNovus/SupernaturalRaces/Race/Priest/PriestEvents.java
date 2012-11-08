package com.TeamNovus.SupernaturalRaces.Race.Priest;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Race.Demon.DemonRace;
import com.TeamNovus.SupernaturalRaces.Race.Vampire.VampireRace;
import com.TeamNovus.SupernaturalRaces.Race.Werewolf.WerewolfRace;

public class PriestEvents implements SNEventListener {
	
	@SNEventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		event.setDamage((int) (event.getDamage() * .80));
	}
	
	@SNEventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer target = SupernaturalRaces.getPlayerManager().getPlayer((Player) event.getEntity());
			Race race = SupernaturalRaces.getRaceManager().getRace(target);
			if(race instanceof DemonRace || race instanceof VampireRace || race instanceof WerewolfRace) {
				event.setDamage((int) (event.getDamage() * 1.25));
			}
		}
	}
}

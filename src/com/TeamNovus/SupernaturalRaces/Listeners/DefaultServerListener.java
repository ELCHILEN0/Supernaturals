package com.TeamNovus.SupernaturalRaces.Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.SNEffect;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Events.EffectEvent;

public class DefaultServerListener implements Listener {

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		
	}
	
	@EventHandler
	public void onLightningStrike(LightningStrikeEvent event) {
		
	}
	
	public void onServerTick() {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			SNPlayer p = SupernaturalRaces.getPlayerManager().getPlayer(player);
			ArrayList<SNEffect> effects = new ArrayList<SNEffect>();
			effects.addAll(p.getEffects());
			effects.addAll(p.getPlayerRace().effects());
			
			for(SNEffect effect : effects) {
				if(effect.getDuration() > 0 || effect.getDuration() == -1) {
					if(effect.getDuration() % effect.getPeriod() == 0) {
						Bukkit.getPluginManager().callEvent(new EffectEvent(player, effect));
					}
					
					if(effect.getDuration() != -1) {
						SupernaturalRaces.getPlayerManager().updateEffect(player, new SNEffect(0, effect.getDuration() - 1, effect.getPeriod(), effect.getModifiers(), effect.getType()));
					}
				} else if(effect.getDuration() == 0) {
					SupernaturalRaces.getPlayerManager().removeEffect(player, effect);
				}
			}
		}
	}
	
}

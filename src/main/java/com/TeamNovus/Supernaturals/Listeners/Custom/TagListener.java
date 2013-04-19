package com.TeamNovus.Supernaturals.Listeners.Custom;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Events.PlayerClassChangeEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.comphenix.taghelper.ReceiveNameTagEvent;
import com.comphenix.taghelper.TagHelperMod;

public class TagListener implements Listener {
	
	@EventHandler
	public void onRecieveNameTag(ReceiveNameTagEvent event) {
		SNPlayer player = SNPlayers.i.get(event.getWatched());

		event.setTag(player.getPlayerClass().getColor() + event.getWatched().getName());
	}
	
	@EventHandler
	public void onPlayerClassChange(final PlayerClassChangeEvent event) {
		PluginManager pluginManager = Bukkit.getPluginManager();
		
		if(pluginManager.isPluginEnabled("TagHelper")) {
			final TagHelperMod tagHelper = (TagHelperMod) pluginManager.getPlugin("TagHelper");
	
			if(tagHelper != null) {
				Bukkit.getScheduler().runTaskLater(Supernaturals.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						tagHelper.refreshPlayer(event.getPlayer());		
					}
				}, 1);
			}
		}
	}
	
}

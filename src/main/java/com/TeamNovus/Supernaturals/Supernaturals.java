package com.TeamNovus.Supernaturals;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.Supernaturals.Commands.BaseCommandExecutor;
import com.TeamNovus.Supernaturals.Commands.CommandManager;
import com.TeamNovus.Supernaturals.Commands.Common.AdminCommands;
import com.TeamNovus.Supernaturals.Commands.Common.DefaultCommands;
import com.TeamNovus.Supernaturals.Commands.Common.PluginCommands;
import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Enchantment.EnchantmentListener;
import com.TeamNovus.Supernaturals.Custom.Recipe.RecipeListener;
import com.TeamNovus.Supernaturals.Database.Database;
import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Items.Recipes;
import com.TeamNovus.Supernaturals.Listeners.EntityListener;
import com.TeamNovus.Supernaturals.Listeners.PlayerListener;
import com.TeamNovus.Supernaturals.Listeners.EffectListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.ExperienceListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.HungerListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.KillDeathListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.TagListener;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Statistics.Cooldown;
import com.TeamNovus.Supernaturals.Tasks.CooldownTask;
import com.TeamNovus.Supernaturals.Tasks.EntityTickTask;
import com.TeamNovus.Supernaturals.Tasks.ManaRegainTask;

public class Supernaturals extends JavaPlugin {
	public static Supernaturals plugin = null;
	
	public void onEnable() {		
		plugin = this;
				
		// Load Config:
		if(!(new File(getDataFolder(), "config.yml").exists())) {
			saveDefaultConfig();
		}

		// Create database structure:
		Database.createStructure(SNPlayer.class);
		Database.createStructure(SNEntity.class);
		Database.createStructure(Effect.class);
		Database.createStructure(Cooldown.class);
		
		// Primary Listeners:
		Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new EffectListener(), this);

		// Custom Field Listeners:
		Bukkit.getPluginManager().registerEvents(new HungerListener(), this);
		Bukkit.getPluginManager().registerEvents(new ExperienceListener(), this);
		Bukkit.getPluginManager().registerEvents(new KillDeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new TagListener(), this);

		// Custom Minecraft:
		Bukkit.getPluginManager().registerEvents(new EnchantmentListener(), this);
		Bukkit.getPluginManager().registerEvents(new RecipeListener(), this);
		
		// Schedule Tasks:
		Bukkit.getScheduler().runTaskTimer(this, new EntityTickTask(), 20, 20); // Every second...
		Bukkit.getScheduler().runTaskTimer(this, new CooldownTask(), 20, 20); // Every second...
		Bukkit.getScheduler().runTaskTimer(this, new ManaRegainTask(), 200, 200); // Every 10 seconds...
		
		// Register the Wand Recipes:
		Recipes.register();
		
		System.out.println(SNClasses.getBaseClass());
		
		// Commands:
		getCommand("supernaturals").setExecutor(new BaseCommandExecutor());

		CommandManager.register(DefaultCommands.class);
		CommandManager.register(PluginCommands.class);
		CommandManager.register(AdminCommands.class);			
	}

	public void onDisable() {		
		// Unscheudle all the tasks.
		Bukkit.getScheduler().cancelTasks(this);
		
		// Nullify any static references.
		plugin = null;
	}
}

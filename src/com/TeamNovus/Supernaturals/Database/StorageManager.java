package com.TeamNovus.Supernaturals.Database;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import com.TeamNovus.Supernaturals.SNClasses;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class StorageManager {
	public enum DatabaseType {
		MySQL, SQLite;
	}

	private Driver driver;

	public StorageManager(DatabaseType type) {
				if(type.equals(DatabaseType.MySQL)) {
					driver = new MySQL("127.0.0.1", 
							"3606", 
							"root", 
							"root", 
							"elchilen0");
//					driver = new MySQL(Supernaturals.getPlugin().getConfig().getString(host), 
//							Supernaturals.getPlugin().getConfig().getString(port), 
//							Supernaturals.getPlugin().getConfig().getString(username), 
//							Supernaturals.getPlugin().getConfig().getString(password), 
//							Supernaturals.getPlugin().getConfig().getString(database));
		} else if(type.equals(DatabaseType.SQLite)) {
			driver = new SQLite(Supernaturals.getPlugin().getConfig().getString(Supernaturals.getPlugin().getDataFolder() + File.separator + "data.dat"));
		}

		if(driver != null) {
			try {
				driver.connect();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Supernaturals.getPlugin().getLogger().warning("The specified driver was not found.  Disabling plugin...");
			Bukkit.getServer().getPluginManager().disablePlugin(Supernaturals.getPlugin());
		}
	}


	/*
	 * ID
	 * Name
	 * Class
	 * Experience
	 * Speed 
	 * Mana
	 * Health
	 * Food_Level
	 * Bound_ID
	 * 
	 */
	public void loadPlayers() {
		PreparedStatement statement;
		try {
			statement = driver.getConnection().prepareStatement("SELECT * FROM `sn_players`");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				SNPlayer player = new SNPlayer();

				player.setId(result.getInt(1));
				player.setName(result.getString(2));
				player.setPlayerClass(SNClasses.i.getExactClass(result.getString(3)), false);
				player.setExperience(result.getInt(4));
				player.setSpeed(result.getFloat(5));
				player.setMana(result.getInt(6), false);
				player.setHealth(result.getInt(7));
				player.setHealth(result.getInt(8));
				player.setFoodLevel(result.getInt(9));
				player.setBinding(result.getInt(10));

				SNPlayers.i.attach(player);
			}
			
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void savePlayers() {
		PreparedStatement statement;
		try {
			statement = driver.getConnection().prepareStatement("INSERT INTO `mydb`.`player` (`id`, `name`, `class`, `experience`, `speed`, `mana`, `health`, `food_level`, `binding`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
			for(SNPlayer player : SNPlayers.i.getAllPlayers()) {
				statement.setInt(0, player.getId());
				statement.setString(1, player.getName());
				statement.setString(2, player.getPlayerClass().getName());
				statement.setInt(4, player.getExperience());
				statement.setFloat(5, player.getSpeed());
				statement.setInt(6, player.getMana());
				statement.setInt(7, player.getHealth());
				statement.setInt(8, player.getFoodLevel());
				statement.setInt(9, player.getBinding());
			}
			
			statement = driver.getConnection().prepareStatement("SELECT * FROM `sn_players`");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				SNPlayer player = new SNPlayer();

				player.setId(result.getInt(1));
				player.setName(result.getString(2));
				player.setPlayerClass(SNClasses.i.getExactClass(result.getString(3)), false);
				player.setExperience(result.getInt(4));
				player.setSpeed(result.getFloat(5));
				player.setMana(result.getInt(6), false);
				player.setHealth(result.getInt(7));
				player.setHealth(result.getInt(8));
				player.setFoodLevel(result.getInt(9));
				player.setBinding(result.getInt(10));

				SNPlayers.i.attach(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}

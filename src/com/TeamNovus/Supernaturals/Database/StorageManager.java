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

	private static StorageManager instance = null;

	public static StorageManager getInstance() {
		if(instance == null)
			instance = new StorageManager();

		return instance;
	}

	private Driver driver;

	public StorageManager() {
		if(driver != null) return;

		String type = Supernaturals.getPlugin().getConfig().getString("database.type");

		if(type.equalsIgnoreCase(DatabaseType.MySQL.name())) {
			String host = Supernaturals.getPlugin().getConfig().getString("database.mysql.host");
			String port = Supernaturals.getPlugin().getConfig().getString("database.mysql.port");
			String username = Supernaturals.getPlugin().getConfig().getString("database.mysql.username");
			String password = Supernaturals.getPlugin().getConfig().getString("database.mysql.password");
			String database = Supernaturals.getPlugin().getConfig().getString("database.mysql.database");

			driver = new MySQL(host, port, username, password, database);
		} else {
			String fileName = Supernaturals.getPlugin().getConfig().getString("database.sqlite.file");

			driver = new SQLite(Supernaturals.getPlugin().getDataFolder() + File.separator + fileName);
		}

		try {
			driver.connect();

			setup();
		} catch (Exception e) {
			e.printStackTrace();
			Supernaturals.getPlugin().getLogger().warning("The specified driver was not found.  Disabling plugin...");
			Bukkit.getServer().getPluginManager().disablePlugin(Supernaturals.getPlugin());
		}
	}

	public void setup() {	
		try {
			PreparedStatement statement = driver.getConnection().prepareStatement("CREATE  TABLE IF NOT EXISTS `sn_players` (" +
					"`id` INT NOT NULL ," +
					"`name` VARCHAR(16) NOT NULL ," +
					"`class` TEXT NULL ," +
					"`experience` INT NULL ," +
					"`speed` FLOAT NULL ," +
					"`mana` INT NULL ," +
					"`health` INT NULL ," +
					"`food_level` INT NULL ," +
					"`bound_id` INT NULL ," +
					"PRIMARY KEY (`id`) )" +
					"ENGINE = InnoDB");

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadPlayers() {
		try {
			PreparedStatement statement = driver.getConnection().prepareStatement("SELECT * FROM `sn_players`");

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
				player.setFoodLevel(result.getInt(8));
				player.setBinding(result.getInt(9));

				SNPlayers.i.attach(player);
			}

			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void savePlayers() {	
		try {
			PreparedStatement count = driver.getConnection().prepareStatement("SELECT COUNT(*) FROM `sn_players` WHERE `name` = ?");

			PreparedStatement insert = driver.getConnection().prepareStatement("INSERT INTO `sn_players` (`id`, `name`, `class`, `experience`, `speed`, `mana`, `health`, `food_level`, `bound_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement update = driver.getConnection().prepareStatement("UPDATE `sn_players` SET `id` = ?, `class` = ?, `experience` = ?, `speed` = ?, `mana` = ?, `health` = ?, `food_level` = ?, `bound_id` = ? WHERE `name` = ?");

			for(SNPlayer player : SNPlayers.i.getAllPlayers()) {
				count.setString(1, player.getName());

				ResultSet result = count.executeQuery();

				if(result.next() && result.getInt(1) == 0) {
					insert.setInt(1, player.getId());
					insert.setString(2, player.getName());
					insert.setString(3, player.getPlayerClass().getName());
					insert.setInt(4, player.getExperience());
					insert.setFloat(5, player.getSpeed());
					insert.setInt(6, player.getMana());
					insert.setInt(7, player.getHealth());
					insert.setInt(8, player.getFoodLevel());
					insert.setInt(9, player.getBinding());

					insert.executeUpdate();
				} else {
					update.setInt(1, player.getId());
					update.setString(2, player.getPlayerClass().getName());
					update.setInt(3, player.getExperience());
					update.setFloat(4, player.getSpeed());
					update.setInt(5, player.getMana());
					update.setInt(6, player.getHealth());
					update.setInt(7, player.getFoodLevel());
					update.setInt(8, player.getBinding());
					update.setString(9, player.getName());

					update.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

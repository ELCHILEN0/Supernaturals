package com.TeamNovus.SupernaturalRaces.Managers;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Database.Driver;
import com.TeamNovus.SupernaturalRaces.Database.MySQL;
import com.TeamNovus.SupernaturalRaces.Database.SQLite;
import com.TeamNovus.SupernaturalRaces.Util.LogUtil;

public class DatabaseManager {

	private Driver driver;
	
	private Connection connection;

	public void connect() {
		String type = SupernaturalRaces.getPlugin().getConfig().getString("storage.type");
		String host = SupernaturalRaces.getPlugin().getConfig().getString("storage.mysql.host");
		String port = SupernaturalRaces.getPlugin().getConfig().getString("storage.mysql.port");
		String database = SupernaturalRaces.getPlugin().getConfig().getString("storage.mysql.database");
		String username = SupernaturalRaces.getPlugin().getConfig().getString("storage.mysql.username");
		String password = SupernaturalRaces.getPlugin().getConfig().getString("storage.mysql.password");
		String file = SupernaturalRaces.getPlugin().getConfig().getString("storage.sqlite.file");
		
		// Determine which database driver to use
		if(type.equalsIgnoreCase("mysql")) {	
			driver = new MySQL(host, port, username, password, database);
		} else {
			driver = new SQLite(new File(SupernaturalRaces.getPlugin().getDataFolder() + File.separator + file).getAbsolutePath());
		}
		
		// This establishes the connection
		try {
			driver.connect();
		} catch (ClassNotFoundException e) {
			LogUtil.info("There was an error with your database settings!  Disabling plugin...");
			SupernaturalRaces.getPlugin().getServer().getPluginManager().disablePlugin(SupernaturalRaces.getPlugin());
		} catch (SQLException e) {
			LogUtil.info("There was an error with your database settings!  Disabling plugin...");
			SupernaturalRaces.getPlugin().getServer().getPluginManager().disablePlugin(SupernaturalRaces.getPlugin());
		}
		
		// Set the connection to the driver's connection
		connection = driver.getConnection();
	}

	public void setup() {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS `sn_players` (" +
					"`player` VARCHAR(16) NOT NULL," +
					"`race` VARCHAR(255) NOT NULL," +
					"`power` INT NOT NULL DEFAULT 0," +
					"`bound_spell_id` INT NOT NULL DEFAULT 0" +
					")");
			
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	// Database Structure:
	//	+-----------+-------+-------+----------------+
	//	| player    | race  | power | bound_spell_id |
	//	+-----------+-------+-------+----------------+
	public void loadPlayer(Player p) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `sn_players` WHERE `player` = ?");
			statement.setString(1, p.getName());
			
			ResultSet result = statement.executeQuery();
			
			SNPlayer player = new SNPlayer();
			player.setName(p.getName());

			if(result.next()) {
				player.setName(result.getString(1));
				player.setRace(result.getString(2));
				player.setPower(result.getInt(3));
				player.setBoundSpellId(result.getInt(4));
			}
		
			SupernaturalRaces.getPlayerManager().putPlayer(player);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void savePlayer(Player p) {
		try {
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(p);

			PreparedStatement count = connection.prepareStatement("SELECT COUNT(*) FROM `sn_players` WHERE `player` = ?");
			PreparedStatement insert = connection.prepareStatement("INSERT INTO `sn_players` (`player`, `race`, `power`, `bound_spell_id`) VALUES (?, ?, ?, ?)");
			PreparedStatement update = connection.prepareStatement("UPDATE `sn_players` SET `race` = ?, `power` = ?, `bound_spell_id` = ? WHERE `player` = ?");

			count.setString(1, p.getName());

			ResultSet result = count.executeQuery();
			result.next();

			if(result.getInt(1) == 0) {
				insert.setString(1, p.getName());
				insert.setString(2, player.getRace());
				insert.setInt(3, player.getPower());
				insert.setInt(4, player.getBoundSpellId());
				insert.executeUpdate();
			} else {
				update.setString(4, p.getName());
				update.setString(1, player.getRace());
				update.setInt(2, player.getPower());
				update.setInt(3, player.getBoundSpellId());
				update.executeUpdate();
			}

			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadPlayers() {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			loadPlayer(p);
		}
	}
	
	public void savePlayers() {
		Iterator<SNPlayer> iterator = SupernaturalRaces.getPlayerManager().getPlayers().iterator();
		while(iterator.hasNext()) {
			SNPlayer player = iterator.next();
			savePlayer(player.getPlayer());
		}
	}
	
	public void unloadPlayer(Player p) {
		savePlayer(p);
		SupernaturalRaces.getPlayerManager().removePlayer(SupernaturalRaces.getPlayerManager().getPlayer(p));
	}
	
	public void close() {
		try {
			driver.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

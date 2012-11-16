package com.TeamNovus.SupernaturalRaces.Database;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Util.LogUtil;

import java.sql.Connection;
import java.util.HashMap;

public class Database {
	private Driver driver;
	
	private Connection connection;

	public void connect() {
		String type = SupernaturalRaces.getPlugin().getConfig().getString("storage.type");
		String host = SupernaturalRaces.getPlugin().getConfig().getString("storage.host");
		String port = SupernaturalRaces.getPlugin().getConfig().getString("storage.port");
		String database = SupernaturalRaces.getPlugin().getConfig().getString("storage.database");
		String username = SupernaturalRaces.getPlugin().getConfig().getString("storage.username");
		String password = SupernaturalRaces.getPlugin().getConfig().getString("storage.password");
		
		// Determine which database driver to use
		if(type.equalsIgnoreCase("mysql")) {	
			driver = new MySQL(host, port, username, password, database);
		} else {
			driver = new SQLite(new File(SupernaturalRaces.getPlugin().getDataFolder() + File.separator + "data.dat").getAbsolutePath());
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
				    "`id` int(11) NOT NULL," +
					"`player` VARCHAR(16) NOT NULL," +
					"`race` VARCHAR(255) NOT NULL," +
					"`power` INT NOT NULL DEFAULT 0" +
					")");
			
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	// Database Structure:
	//	+----+-----------+-------+-------+
	//	| id | player    | race  | power |
	//	+----+-----------+-------+-------+
	public void load() {
		HashMap<String, Integer> mappings = new HashMap<String, Integer>();
		HashMap<Integer, SNPlayer> players = new HashMap<Integer, SNPlayer>();		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `sn_players`");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				// Load the mappings from the Database
				mappings.put(result.getString(2), result.getInt(1));
				
				// Construct the SNPlayer
				SNPlayer player = new SNPlayer();
				player.setRace(result.getString(3));
				player.setPower(result.getInt(4));
				
				// Add the ID and the SNPlayer
				players.put(result.getInt(1), player);
			}			
			SupernaturalRaces.getPlayerManager().setPlayers(mappings, players);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			for(Integer id : SupernaturalRaces.getPlayerManager().getPlayers().keySet()) {
				String name = SupernaturalRaces.getPlayerManager().getName(id);
				SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(id);
				
				PreparedStatement count = connection.prepareStatement("SELECT COUNT(*) FROM `sn_players` WHERE `player` = ?");
				PreparedStatement insert = connection.prepareStatement("INSERT INTO `sn_players` (`id`, `player`, `race`, `power`) VALUES (?, ?, ?, ?)");
				PreparedStatement update = connection.prepareStatement("UPDATE `sn_players` SET `id` = ?, `race` = ?, `power` = ? WHERE `player` = ?");
			
				count.setString(1, name);
				
				ResultSet result = count.executeQuery();
				result.next();

				if(result.getInt(1) == 0) {
					insert.setInt(1, id);
					insert.setString(2, name);
					insert.setString(3, player.getRace());
					insert.setInt(4, player.getPower());
					insert.executeUpdate();
				} else {
					update.setInt(1, id);
					update.setString(4, name);
					update.setString(2, player.getRace());
					update.setInt(3, player.getPower());
					update.executeUpdate();
				}
				
				result.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			driver.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
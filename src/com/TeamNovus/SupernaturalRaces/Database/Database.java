package com.TeamNovus.SupernaturalRaces.Database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

import java.sql.Connection;
import java.util.HashMap;

public class Database {
	private Connection connection;

	public void connect() {
		String host = SupernaturalRaces.getPlugin().getConfig().getString("storage.host");
		String port = SupernaturalRaces.getPlugin().getConfig().getString("storage.port");
		String database = SupernaturalRaces.getPlugin().getConfig().getString("storage.database");
		String username = SupernaturalRaces.getPlugin().getConfig().getString("storage.username");
		String password = SupernaturalRaces.getPlugin().getConfig().getString("storage.password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setup() {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS `sn_players` (" +
				    "`id` int(11) NOT NULL AUTO_INCREMENT," +
					"`player` VARCHAR(16) NOT NULL," +
					"`race` VARCHAR(255) NOT NULL," +
					"`power` INT NOT NULL DEFAULT 0," +
					"PRIMARY KEY (`id`)," +
					"UNIQUE KEY `id_UNIQUE` (`id`)" +
					")");
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	// Database Structure:
	//	+-----+----------+-------+-------+
	//	| id  |  player  | race  | power |
	//	+-----+----------+-------+-------+
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
			
//			for(String name : SupernaturalRaces.getPlayerManager().getPlayers().keySet()) {
//				
//				PreparedStatement count = connection.prepareStatement("SELECT COUNT(*) FROM `sn_players` WHERE `player` = ?");
//				PreparedStatement insert = connection.prepareStatement("INSERT INTO `sn_players` (`id`, `player`, `race`, `power`) VALUES (?, ?, ?, ?)");
//				PreparedStatement update = connection.prepareStatement("UPDATE `sn_players` SET `id` = ?, `race` = ?, `power` = ? WHERE `player` = ?");
//				
//				count.setString(1, name);
//				SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(name);
//				
//				ResultSet result = count.executeQuery();
//				result.next();
//
//				if(result.getInt(1) == 0) {
//					insert.setString(1, name);
//					insert.setString(2, player.getRace());
//					insert.setInt(3, player.getPower());
//					insert.executeUpdate();
//				} else {
//					update.setString(3, name);
//					update.setString(1, player.getRace());
//					update.setInt(2, player.getPower());
//					update.executeUpdate();
//				}
//				
//				result.close();
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
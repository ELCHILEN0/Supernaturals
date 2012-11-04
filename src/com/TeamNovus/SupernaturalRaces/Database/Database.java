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
	
	public void load() {
		HashMap<String, SNPlayer> players = new HashMap<String, SNPlayer>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `sn_players`");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				SNPlayer player = new SNPlayer();
				player.setRace(result.getString(2));
				player.setPower(result.getInt(3));
				players.put(result.getString(1), player);
			}
			SupernaturalRaces.getPlayerManager().setPlayers(players);
			for(SNPlayer player: SupernaturalRaces.getPlayerManager().getPlayers().values()) {
				System.out.println(player.getRace());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			for(String name : SupernaturalRaces.getPlayerManager().getPlayers().keySet()) {
				PreparedStatement count = connection.prepareStatement("SELECT COUNT(*) FROM `sn_players` WHERE `player` = ?");
				PreparedStatement insert = connection.prepareStatement("INSERT INTO `sn_players` (`player`, `race`, `power`) VALUES (?, ?, ?)");
				PreparedStatement update = connection.prepareStatement("UPDATE `sn_players` SET `race` = ?, `power` = ? WHERE `player` = ?");
				
				count.setString(1, name);
				SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(name);
				
				ResultSet result = count.executeQuery();
				result.next();

				if(result.getInt(1) == 0) {
					insert.setString(1, name);
					insert.setString(2, player.getRace());
					insert.setInt(3, player.getPower());
					insert.executeUpdate();
				} else {
					update.setString(3, name);
					update.setString(1, player.getRace());
					update.setInt(2, player.getPower());
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
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
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
	protected Connection connection;

	final protected synchronized void connect() throws ClassNotFoundException, SQLException {
		String host = SupernaturalRaces.getPlugin().getConfig().getString("storage.host");
		String port = SupernaturalRaces.getPlugin().getConfig().getString("storage.port");
		String database = SupernaturalRaces.getPlugin().getConfig().getString("storage.database");
		String username = SupernaturalRaces.getPlugin().getConfig().getString("storage.username");
		String password = SupernaturalRaces.getPlugin().getConfig().getString("storage.password");

		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
	}

	final protected synchronized void setup() {
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
	
	final protected synchronized void load() {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
package com.TeamNovus.Supernaturals.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TeamNovus.Supernaturals.SNClasses;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class StorageManager {
	public enum DatabaseType {
		MySQL, SQLite;
	}
	
	private Driver driver;
	
	public StorageManager(DatabaseType type) {
//		if(type.equals(DatabaseType.MySQL)) {
//			driver = new MySQL(Supernaturals.getPlugin().getConfig().getString(host), 
//										Supernaturals.getPlugin().getConfig().getString(port), 
//										Supernaturals.getPlugin().getConfig().getString(username), 
//										Supernaturals.getPlugin().getConfig().getString(password), 
//										Supernaturals.getPlugin().getConfig().getString(database));
//		} else if(type.equals(DatabaseType.SQLite)) {
//			driver = new SQLite(Supernaturals.getPlugin().getConfig().getString(path));
//		}
		
//		if(driver != null) {
//			driver.connect();
//		} else {
//			Supernaturals.getPlugin().getLogger().warning("The specified driver was not found.  Disabling plugin...");
//			Bukkit.getServer().getPluginManager().disablePlugin(Supernaturals.getPlugin());
//		}
	}
	
	
	/*
	 * Id - Name - Class - Experience - Speed - Mana - Health - Hunger - BoundID
	 * 
	 * 
	 */
	public void loadPlayers() {
		PreparedStatement statment;
		try {
			statment = driver.getConnection().prepareStatement("SELECT * FROM `sn_players`");

			ResultSet result = statment.executeQuery();
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

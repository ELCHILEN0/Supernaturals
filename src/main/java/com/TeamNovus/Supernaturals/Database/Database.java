package com.TeamNovus.Supernaturals.Database;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.TeamNovus.Persistence.Databases.Configuration;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLConfiguration;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLDatabase;
import com.TeamNovus.Persistence.Queries.Queries.DeleteQuery;
import com.TeamNovus.Persistence.Queries.Queries.InsertQuery;
import com.TeamNovus.Persistence.Queries.Queries.SelectQuery;
import com.TeamNovus.Persistence.Queries.Queries.UpdateQuery;
import com.TeamNovus.Supernaturals.Supernaturals;

public class Database {
	private static com.TeamNovus.Persistence.Databases.Database database;

	private static com.TeamNovus.Persistence.Databases.Database connect() {
		Configuration configuration = null;

		FileConfiguration config = Supernaturals.plugin.getConfig();

		if(config.getString("database.type").equalsIgnoreCase("mysql")) {
			configuration = new MySQLConfiguration(config.getString("database.mysql.host"),
													config.getString("database.mysql.port"), 
													config.getString("database.mysql.database"), 
													config.getString("database.mysql.username"),
													config.getString("database.mysql.password"));

			try {
				database = new MySQLDatabase(configuration);
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		} 

		if(database == null){
			Bukkit.getLogger().severe("-----------------------------------------------------");
			Bukkit.getLogger().severe("Unable to create a database instance!");
			Bukkit.getLogger().severe("-----------------------------------------------------");

			return null;
		}

		database.connect();
		
		if(config.getBoolean("database.log-queries")) {
			database.enableLogging();
		} else {
			database.disableLogging();
		}

		if(database.isDisconnected()) {
			Bukkit.getLogger().severe("-----------------------------------------------------");
			Bukkit.getLogger().severe("Unable to connect to the database!");
			Bukkit.getLogger().severe("Verify that your information is correct then try again!");
			Bukkit.getLogger().severe("-----------------------------------------------------");

			Bukkit.getServer().getPluginManager().disablePlugin(Supernaturals.plugin);

			return null;
		}


		Bukkit.getLogger().info("-----------------------------------------------------");
		Bukkit.getLogger().info("Database connection sucessful!");
		Bukkit.getLogger().info("-----------------------------------------------------");

		return database;
	}
	
	private static com.TeamNovus.Persistence.Databases.Database database() {
		if(database == null) {
			return connect();
		}

		return database;
	}


    public static void createStructure(Class<?> aClass) {
        database().createStructure(aClass);
    }

    public static void updateStructure(Class<?> aClass) {
       database().updateStructure(aClass);
    }

    public static <T> T find(Class<T> tClass, int i) {
        return database().find(tClass, i);
    }

    public static <T> T find(Class<T> tClass, long l) {
        return database().find(tClass, l);
    }

    public static <T> boolean save(T t) {
        return database().save(t);
    }

    public static <T> boolean drop(T t) {
        return database().drop(t);
    }

    public static <T> List<T> findAll(Class<T> tClass) {
        return database().findAll(tClass);
    }

    public static void saveAll(Iterable<?> objects) {
        database().saveAll(objects);
    }

    public static void dropAll(Iterable<?> objects) {
        database().drop(objects);
    }
    
	public static <T> SelectQuery<T> select(Class<T> objectClass) {
		return database().select(objectClass);
	}
	
	public static <T> InsertQuery<T> insert(Class<T> objectClass) {
		return database().insert(objectClass);
	}
	
	public static <T> UpdateQuery<T> update(Class<T> objectClass) {
		return database().update(objectClass);
	}
	
	public static <T> DeleteQuery<T> delete(Class<T> objectClass) {
		return database().delete(objectClass);
	}

    public static void beginTransaction() {
        database().beginTransaction();
    }

    public static void endTransaction() {
        database().endTransaction();
    }
}

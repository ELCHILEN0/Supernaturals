package com.TeamNovus.SupernaturalRaces.Util;

import java.util.logging.Level;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;

public class LogUtil {	
	public static void info(String message) {
		SupernaturalRaces.getPlugin().getLogger().log(Level.INFO, message);
	}
	
	public static void warning(String message) {
		SupernaturalRaces.getPlugin().getLogger().log(Level.WARNING, message);
	}
	
	public static void error(String message) {
		SupernaturalRaces.getPlugin().getLogger().log(Level.SEVERE, message);
	}
	
	public static void debug(Level level, String message) {
		SupernaturalRaces.getPlugin().getLogger().log(level, message);
	}
}

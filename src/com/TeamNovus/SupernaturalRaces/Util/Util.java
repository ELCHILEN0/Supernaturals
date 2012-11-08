package com.TeamNovus.SupernaturalRaces.Util;

public class Util {

	public static boolean isInt(String s) {
		try {
			Integer.valueOf(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}

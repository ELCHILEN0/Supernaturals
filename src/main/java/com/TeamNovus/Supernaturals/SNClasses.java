package com.TeamNovus.Supernaturals;

import com.TeamNovus.Supernaturals.Classes.Human;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class SNClasses {
	private static SNClass baseClass = new Human();
	
	public static SNClass getBaseClass() {
		return baseClass;
	}
	
	public static SNClass getBestClass(String s) {
		return getBestClass(s, baseClass);
	}
	
	public static SNClass getExactClass(String s) {
		return getExactClass(s, baseClass);
	}
	
	private static SNClass getBestClass(String s, SNClass startClass) {
		SNClass target = null;
				
		if(startClass.getName().toLowerCase().startsWith(s.toLowerCase())) {
			target = startClass;
		}
		
		for(SNClass c : startClass.getAllJoinableClasses()) {	
			if(target != null) break;
			
			if(c.getName().toLowerCase().startsWith(s.toLowerCase())) {
				target = c;				
				break;
			}
						
			target = getBestClass(s, c);
		}
		
		return target;
	}
	
	private static SNClass getExactClass(String s, SNClass startClass) {
		SNClass target = null;
		
		if(startClass.getName().equals(s)) {
			target = startClass;
		}
		
		for(SNClass c : startClass.getAllJoinableClasses()) {
			if(target != null) break;
			
			if(c.getName().equals(s)) {
				target = c;
				break;
			}
						
			target = getBestClass(s, c);
		}
		
		return target;
	}
}
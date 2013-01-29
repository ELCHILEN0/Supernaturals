package com.TeamNovus.Supernaturals.Util;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class SNClassUtil {
	
	public static SNClass getBestClass(String s, SNClass startClass) {
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
	
	public static SNClass getExactClass(String s, SNClass startClass) {
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

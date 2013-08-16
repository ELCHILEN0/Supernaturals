package com.TeamNovus.Supernaturals.Collections;

import com.TeamNovus.Supernaturals.Classes.Human;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class SNClassCollection {
	private SNClass baseClass = new Human();
	
	public SNClass getBaseClass() {
		return baseClass;
	}
	
	public SNClass getBestClass(String s) {
		return getBestClass(s, baseClass);
	}
	
	public SNClass getExactClass(String s) {
		return getExactClass(s, baseClass);
	}
	
	private SNClass getBestClass(String s, SNClass startClass) {
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
	
	private SNClass getExactClass(String s, SNClass startClass) {
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

package com.TeamNovus.SupernaturalRaces.Models;

import java.util.List;

import com.TeamNovus.SupernaturalRaces.Event.SNEventListener;

public interface SNRaceNew {
	
	List<Class<? extends SNEventListener>> events();
	
}

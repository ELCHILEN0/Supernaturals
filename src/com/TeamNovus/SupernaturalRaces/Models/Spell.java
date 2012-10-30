package com.TeamNovus.SupernaturalRaces.Models;

import com.TeamNovus.SupernaturalRaces.Events.SkillPostprocessEvent;
import com.TeamNovus.SupernaturalRaces.Events.SkillPreprocessEvent;

public interface Spell {
	/**
	 * The name of the skill
	 * Must be unique
	 */
	String name();
	/**
	 * Available aliases for the skill
	 * @return
	 */
	String[] alias();
	
	/**
	 * Called when the skill is to be executed
	 * This is the bulk of the interface
	 */
	void onSkill();
	
	/**
	 * Called before the skill occurs
	 */
	void onSkillPreprocess(SkillPreprocessEvent event);
	/**
	 * Called after the skill occurs
	 */
	void onSkillPostprocess(SkillPostprocessEvent event);
}

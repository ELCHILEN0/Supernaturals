package com.TeamNovus.SupernaturalRaces.Models;

public interface Skill {
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
	 * Called before the skill occurs
	 */
	void onSkillPreprocess();
	/**
	 * Called after the skill occurs
	 */
	void onSkillPostprocess();
}

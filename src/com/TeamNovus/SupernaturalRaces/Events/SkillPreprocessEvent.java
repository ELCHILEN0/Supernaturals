package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.TeamNovus.SupernaturalRaces.Models.Skill;

public class SkillPreprocessEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Entity target;
    private Skill skill;
    
    public SkillPreprocessEvent(Player player, Entity target, Skill skill) {
    	this.player = player;
    	this.target = target;
    	this.skill = skill;
    }
    
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Entity getTarget() {
		return target;
	}

	public Skill getSkill() {
		return skill;
	}
}

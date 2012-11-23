package com.TeamNovus.SupernaturalRaces.Events;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerKillEntityEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private Entity entity;
    private List<ItemStack> drops;
    private Integer droppedExp;
    
    public PlayerKillEntityEvent(Player player, Entity entity, Integer droppedExp, List<ItemStack> list) {
    	super(player);
    	this.entity = entity;
    	this.droppedExp = droppedExp;
    }
    
	public Entity getEntity() {
		return entity;
	}
	
	public Integer droppedExp() {
		return droppedExp;
	}
	
	public void setDroppedExp(Integer droppedExp) {
		this.droppedExp = droppedExp;
	}
	
	public List<ItemStack> getDrops() {
		return drops;
	}
    
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
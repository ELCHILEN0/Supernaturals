package com.TeamNovus.SupernaturalRaces.Event;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SNEventManager implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		// TODO: Loop through the players race triggering any events it finds
		for(Method method : TestEventListener.class.getMethods()) {
			if(method.isAnnotationPresent(SNEventHandler.class)) {
				for(Type type : method.getParameterTypes()) {
					System.out.println(type.toString());
					if(type.equals(PlayerMoveEvent.class)) {
						System.out.println("The Type is an InstanceOf PlayerMoveEvent");
					}
				}
			}
		}
	}

}

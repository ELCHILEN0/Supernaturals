package com.TeamNovus.Supernaturals.Custom.Effect;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

public @interface EffectTypeListener {
	Class<? extends Event> eventClass() default Event.class;
	EventPriority priority() default EventPriority.NORMAL;
}

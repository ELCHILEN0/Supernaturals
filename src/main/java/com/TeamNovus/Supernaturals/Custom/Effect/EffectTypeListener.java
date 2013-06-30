package com.TeamNovus.Supernaturals.Custom.Effect;

import org.bukkit.event.Event;

public @interface EffectTypeListener {
	Class<? extends Event> eventClass() default Event.class;
}

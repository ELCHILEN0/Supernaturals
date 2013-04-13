package com.TeamNovus.Persistence.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
	
	/**
	 * The name of the table for the database.
	 * 
	 * @return The name of the table.
	 */
	String name();
}

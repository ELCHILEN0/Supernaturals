package com.TeamNovus.Persistence.Internal;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.regex.Pattern;

import com.TeamNovus.Persistence.Annotations.Column;
import com.TeamNovus.Persistence.Exceptions.ColumnRegistrationException;

public class ColumnRegistrationFactory {

	public static ColumnRegistration getColumnRegistration(Field field) throws ColumnRegistrationException {
		// Check to make sure that the Column annotation is present
		if(!(field.isAnnotationPresent(Column.class))) {
			throw new ColumnRegistrationException("Class '" + field.getClass().getCanonicalName() + "' does not have a Column annotation present.");
		}

		// Check that 'name' is only made up of allowed characters (Alphanumeric and '_')
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]*$");
		if (!(pattern.matcher(field.getAnnotation(Column.class).name()).find())) {
			throw new ColumnRegistrationException("Class '" + field.getClass().getCanonicalName() + "' column name has illegal characters in it. The name is limited to alphanumeric characters and '_'.");
		}

		// If the class passes all checks, return a new column registration.
		return new ColumnRegistration(field.getAnnotation(Column.class), field);
	}
	
	public static LinkedList<ColumnRegistration> getColumnRegistrations(Class<?> clazz) {
		LinkedList<ColumnRegistration> columns = new LinkedList<ColumnRegistration>();
		
		for(Field field : clazz.getDeclaredFields()) {
			try {
				columns.add(ColumnRegistrationFactory.getColumnRegistration(field));
			} catch (ColumnRegistrationException ignored) { }
		}
		
		return columns;
	}
	
}

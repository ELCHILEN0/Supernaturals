package com.TeamNovus.Persistence.Internal;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import com.TeamNovus.Persistence.Annotations.Column;
import com.TeamNovus.Persistence.Annotations.Key;
import com.TeamNovus.Persistence.Exceptions.ColumnRegistrationException;

public class KeyRegistrationFactory {

	public static ColumnRegistration getKeyColumnRegistration(Class<?> clazz) throws ColumnRegistrationException {
		for(Field field : clazz.getDeclaredFields()) {
			// Check to make sure that the Key annotation is present
			if(!(field.isAnnotationPresent(Key.class))) {
				continue;
			}

			// Check to make sure that the Column annotation is present
			if(!(field.isAnnotationPresent(Column.class))) {
				continue;
			}
			
			// Check that 'name' is only made up of allowed characters (Alphanumeric and '_')
			Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]*$");
			if (!(pattern.matcher(field.getAnnotation(Column.class).name()).find())) {
				throw new ColumnRegistrationException("Class '" + field.getClass().getCanonicalName() + "' column name has illegal characters in it. The name is limited to alphanumeric characters and '_'.");
			}

			// If the class passes all checks, return a new column registration.
			return new ColumnRegistration(field.getAnnotation(Column.class), field);
		}
		
		throw new ColumnRegistrationException("Class '" + clazz.getCanonicalName() + "' does not contain a key column!");
	}
	
}

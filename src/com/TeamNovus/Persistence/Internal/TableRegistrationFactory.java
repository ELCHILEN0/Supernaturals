package com.TeamNovus.Persistence.Internal;

import java.util.LinkedList;
import java.util.regex.Pattern;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Exceptions.ColumnRegistrationException;
import com.TeamNovus.Persistence.Exceptions.IdentifierRegistrationException;
import com.TeamNovus.Persistence.Exceptions.TableRegistrationException;

public class TableRegistrationFactory {
	
	/**
	 * Create a TableRegistration from a specific class.
	 * 
	 * @param tableClass - The class to feed the data from.
	 * @return A created TableRegistration or null.
	 * @throws TableRegistrationException 
	 * @throws ColumnRegistrationException 
	 * @throws IdentifierRegistrationException 
	 */
	public static TableRegistration createTableRegistration(Class<?> tableClass) throws TableRegistrationException {
		// Check to make sure that the Table annotation is present
		if(!(tableClass.isAnnotationPresent(Table.class))) {
			throw new TableRegistrationException("Class '" + tableClass.getCanonicalName() + "' does not have a Table annotation present.");
		}

		// Check that 'name' is only made up of allowed characters (Alphanumeric and '_')
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]*$");
		if (!(pattern.matcher(tableClass.getAnnotation(Table.class).name()).find())) {
			throw new TableRegistrationException("Class '" + tableClass.getCanonicalName() + "' table name has illegal characters in it. The name is limited to alphanumeric characters and '_'.");
		}
		
		ColumnRegistration key = null;
		LinkedList<ColumnRegistration> columns = new LinkedList<ColumnRegistration>();
		
		try {
			key = KeyRegistrationFactory.getKeyColumnRegistration(tableClass);
			columns = ColumnRegistrationFactory.getColumnRegistrations(tableClass);
		} catch (Exception ignored) { }

		// If the class passes all checks, return a new table registration.
		return new TableRegistration(tableClass.getAnnotation(Table.class), 
											tableClass, 
											key,
											columns);
	}
}

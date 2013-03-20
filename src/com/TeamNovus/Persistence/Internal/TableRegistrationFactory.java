package com.TeamNovus.Persistence.Internal;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.LinkedList;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Relationships.CascadeType;
import com.TeamNovus.Persistence.Annotations.Relationships.OneToMany;
import com.TeamNovus.Persistence.Annotations.Relationships.OneToOne;
import com.TeamNovus.Persistence.Annotations.Relationships.RelationshipType;
import com.TeamNovus.Persistence.Exceptions.TableRegistrationException;

public class TableRegistrationFactory {
	
	public static TableRegistration getTableRegistration(Class<?> tableClass) throws TableRegistrationException {
		
		// Check if the Table annotation is present
		if(!(tableClass.isAnnotationPresent(Table.class))) {
			throw new TableRegistrationException("Class '" + tableClass.getCanonicalName() + "' does not have a Table annotation present.");
		}
		
		// Check if the table has an Id annotation
		if(ColumnRegistrationFactory.getIdRegistration(tableClass) == null) {
			throw new TableRegistrationException("Class '" + tableClass.getCanonicalName() + "' does not have an Id annotation present.");
		}
		
		Table annotation = tableClass.getAnnotation(Table.class);
		ColumnRegistration id = ColumnRegistrationFactory.getIdRegistration(tableClass);
		LinkedList<ColumnRegistration> columns = ColumnRegistrationFactory.getColumnRegistrations(tableClass);

		TableRegistration tableRegistration = new TableRegistration(annotation, tableClass, id, columns);
		tableRegistration.setSubTables(getSubTableRegistrations(tableRegistration, tableClass));
		
		return tableRegistration;
	}
	
	private static SubTableRegistration getSubTableRegistration(TableRegistration parentTableRegistration, Field field) throws TableRegistrationException {
		// Make sure the field actually is a Sub-Relationship
		if(!(field.isAnnotationPresent(OneToOne.class)) && !(field.isAnnotationPresent(OneToMany.class))) {
			throw new TableRegistrationException("Field '" + field.getName() + "' is not mapped by either OneToOne or OneToMany.");
		}
		
		// Get the actual type of the field.  For collections the inner type must be used.
		Class<?> tableClass = (Class<?>) (Collection.class.isAssignableFrom(field.getType()) ? ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0] : field.getType());
		
		// Check if the Table annotation is present
		if(!(tableClass.isAnnotationPresent(Table.class))) {
			throw new TableRegistrationException("Class '" + tableClass.getCanonicalName() + "' does not have a Table annotation present.");
		}
		
		// Check if the table has an Id annotation
		if(ColumnRegistrationFactory.getIdRegistration(tableClass) == null) {
			throw new TableRegistrationException("Class '" + tableClass.getCanonicalName() + "' does not have an Id annotation present.");
		}		
		
		// Check if the table has a ForeignKey annotation
		if(ColumnRegistrationFactory.getForeignKeyRegistration(tableClass) == null) {
			throw new TableRegistrationException("Class '" + tableClass.getCanonicalName() + "' does not have a ForeignKey annotation present.");
		}
		
		Table annotation = tableClass.getAnnotation(Table.class);
		RelationshipType relationshipType = field.isAnnotationPresent(OneToOne.class) ? RelationshipType.ONE_TO_ONE : RelationshipType.ONE_TO_MANY;
		CascadeType cascadeType = field.isAnnotationPresent(OneToOne.class) ? field.getAnnotation(OneToOne.class).cascade() : field.isAnnotationPresent(OneToMany.class) ? field.getAnnotation(OneToMany.class).cascade() : CascadeType.ALL;
		ColumnRegistration id = ColumnRegistrationFactory.getIdRegistration(tableClass);
		ColumnRegistration foreignKey = ColumnRegistrationFactory.getForeignKeyRegistration(tableClass);
		LinkedList<ColumnRegistration> columns = ColumnRegistrationFactory.getColumnRegistrations(tableClass);
		
		SubTableRegistration subTableRegistration = new SubTableRegistration(annotation, tableClass, relationshipType, cascadeType, id, foreignKey, columns, parentTableRegistration, field);
		subTableRegistration.setSubTables(getSubTableRegistrations(parentTableRegistration, tableClass));
		
		return subTableRegistration;
	}
	
	private static LinkedList<SubTableRegistration> getSubTableRegistrations(TableRegistration parentTableRegistartion, Class<?> tableClass) {
		LinkedList<SubTableRegistration> subTableRegistartions = new LinkedList<SubTableRegistration>();
		
		for(Field field : tableClass.getDeclaredFields()) {
			if(field.isAnnotationPresent(OneToOne.class) || field.isAnnotationPresent(OneToMany.class)) {
				try {
					subTableRegistartions.add(getSubTableRegistration(parentTableRegistartion, field));
				} catch (TableRegistrationException e) {
					e.printStackTrace();
				}
			}
		}
		
		return subTableRegistartions;
	}

}

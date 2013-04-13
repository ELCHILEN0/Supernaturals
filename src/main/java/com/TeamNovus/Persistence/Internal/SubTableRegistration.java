package com.TeamNovus.Persistence.Internal;

import java.lang.reflect.Field;
import java.util.LinkedList;

import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Persistence.Annotations.Relationships.CascadeType;
import com.TeamNovus.Persistence.Annotations.Relationships.RelationshipType;

public class SubTableRegistration extends TableRegistration {
	private CascadeType cascadeType = null;
	private RelationshipType relationshipType = null;
	private ColumnRegistration foreignKey = null;
	private TableRegistration parentTableRegistration = null;
	private Field parentField = null;

	public SubTableRegistration(Table annotation, Class<?> tableClass, RelationshipType relationshipType, CascadeType cascadeType, ColumnRegistration id, ColumnRegistration foreignKey, LinkedList<ColumnRegistration> columns, TableRegistration parentTableRegistration, Field parentField) {	
		super(annotation, tableClass, foreignKey, columns);
		this.cascadeType = cascadeType;
		this.relationshipType = relationshipType;
		this.foreignKey = foreignKey;
		this.parentTableRegistration = parentTableRegistration;
		this.parentField = parentField;
	}
	
	public RelationshipType getRelationshipType() {
		return relationshipType;
	}

	public CascadeType getCascadeType() {
		return cascadeType;
	}
	
	public ColumnRegistration getForeignKey() {
		return foreignKey;
	}

	public TableRegistration getParentTableRegistration() {
		return parentTableRegistration;
	}
	
	public Field getParentField() {
		return parentField;
	}

}

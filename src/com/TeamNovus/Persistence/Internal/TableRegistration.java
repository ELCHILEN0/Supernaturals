package com.TeamNovus.Persistence.Internal;

import java.util.LinkedList;

import com.TeamNovus.Persistence.Annotations.Table;

public class TableRegistration {
	private Table annotation = null;
	private Class<?> tableClass = null;
	private ColumnRegistration id = null;
	private LinkedList<ColumnRegistration> columns = new LinkedList<ColumnRegistration>();
	private LinkedList<SubTableRegistration> subTables = new LinkedList<SubTableRegistration>();
	
	public TableRegistration(Table annotation, Class<?> tableClass, ColumnRegistration id, LinkedList<ColumnRegistration> columns) {
		this.annotation = annotation;
		this.tableClass = tableClass;
		this.id = id;
		this.columns = columns;
	}
	
	public Table getAnnotation() {
		return annotation;
	}
	
	public String getName() {
		return annotation.name();
	}
	
	public Class<?> getTableClass() {
		return tableClass;
	}
	
	public ColumnRegistration getId() {
		return id;
	}
	
	public LinkedList<ColumnRegistration> getColumns() {
		return columns;
	}
	
	public LinkedList<SubTableRegistration> getSubTables() {
		return subTables;
	}
	
	public TableRegistration setSubTables(LinkedList<SubTableRegistration> subTables) {
		this.subTables = subTables;
		
		return this;
	}
	
}

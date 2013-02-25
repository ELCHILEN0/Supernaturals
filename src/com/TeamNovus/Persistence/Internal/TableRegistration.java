package com.TeamNovus.Persistence.Internal;

import java.util.LinkedList;

import com.TeamNovus.Persistence.Annotations.Table;

public class TableRegistration {
	private Table annotation;
	private Class<?> tableClass;
	private ColumnRegistration key;
	private LinkedList<ColumnRegistration> columns = new LinkedList<ColumnRegistration>();
	
	public TableRegistration(Table annotation, Class<?> tableClass, ColumnRegistration key, LinkedList<ColumnRegistration> columns) {
		this.key = key;
		this.annotation = annotation;
		this.tableClass = tableClass;
		this.columns = columns;
	}
	
	public TableRegistration(Table annotation, Class<?> tableClass) {
		this.annotation = annotation;
		this.tableClass = tableClass;
	}
	
	public String getName() {
		return annotation.name();
	}
	
	public Table getAnnotation() {
		return annotation;
	}
	
	public Class<?> getTableClass() {
		return tableClass;
	}
	
	public ColumnRegistration getKey() {
		return key;
	}
	
	public LinkedList<ColumnRegistration> getColumns() {
		return columns;
	}
	
	public void setColumns(LinkedList<ColumnRegistration> columns) {
		this.columns = columns;
	}
	
}

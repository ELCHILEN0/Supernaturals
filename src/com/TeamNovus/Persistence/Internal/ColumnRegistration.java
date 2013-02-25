package com.TeamNovus.Persistence.Internal;

import java.lang.reflect.Field;

import com.TeamNovus.Persistence.Annotations.Column;

public class ColumnRegistration {
	private Column annotation;
	private Field field;
	
	public ColumnRegistration(Column annotation, Field field) {
		this.annotation = annotation;
		this.field = field;
	}
	
	public Column getAnnotation() {
		return annotation;
	}
	
	public Field getField() {
		return field;
	}
	
	public String getName() {
		return annotation.name();
	}
	
	public String getType() {
		Class<?> type = field.getType();

		if(type.equals(Integer.class) || type.equals(int.class)) {
			return "INT";
		} else if (type.equals(Long.class) || type.equals(long.class)) {
			return "BIGINT";
		} else if (type.equals(Double.class) || type.equals(double.class)) {
			return "DOUBLE";
		} else if (type.equals(String.class)) {
			return "TEXT";
		} else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
			return "TINYINT";
		} else if (type.equals(Short.class) || type.equals(short.class)) {
			return "SMALLINT";
		} else if (type.equals(Float.class) || type.equals(float.class)) {
			return "FLOAT";
		} else if (type.equals(Byte.class) || type.equals(byte.class)) {
			return "TINYINT";
		}
		
		return null;
	}

}

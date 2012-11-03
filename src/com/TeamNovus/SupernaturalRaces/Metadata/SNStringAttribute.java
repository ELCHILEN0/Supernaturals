package com.TeamNovus.SupernaturalRaces.Metadata;

public class SNStringAttribute implements SNAttribute {
	private String value;
	
	public SNStringAttribute(String value) {
		this.value = value;
	}

	@Override
	public SNAttributeType type() {
		return SNAttributeType.STRING;
	}

	@Override
	public String value() {
		return value;
	}
}

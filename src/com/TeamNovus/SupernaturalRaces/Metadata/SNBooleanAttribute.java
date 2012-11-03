package com.TeamNovus.SupernaturalRaces.Metadata;

public class SNBooleanAttribute implements SNAttribute {
	private Boolean value;
	
	public SNBooleanAttribute(Boolean value) {
		this.value = value;
	}

	@Override
	public SNAttributeType type() {
		return SNAttributeType.BOOLEAN;
	}

	@Override
	public Boolean value() {
		return value;
	}
}

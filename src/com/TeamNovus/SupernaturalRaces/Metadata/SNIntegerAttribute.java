package com.TeamNovus.SupernaturalRaces.Metadata;

public class SNIntegerAttribute implements SNAttribute {
	private Integer value;
	
	public SNIntegerAttribute(Integer value) {
		this.value = value;
	}

	@Override
	public SNAttributeType type() {
		return SNAttributeType.INTEGER;
	}

	@Override
	public Integer value() {
		return value;
	}
}

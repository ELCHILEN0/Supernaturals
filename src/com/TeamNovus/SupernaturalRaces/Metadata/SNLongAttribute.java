package com.TeamNovus.SupernaturalRaces.Metadata;

public class SNLongAttribute implements SNAttribute {
	private Long value;
	
	public SNLongAttribute(Long value) {
		this.value = value;
	}

	@Override
	public SNAttributeType type() {
		return SNAttributeType.LONG;
	}

	@Override
	public Long value() {
		return value;
	}
}

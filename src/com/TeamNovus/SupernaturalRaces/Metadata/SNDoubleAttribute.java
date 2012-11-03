package com.TeamNovus.SupernaturalRaces.Metadata;

public class SNDoubleAttribute implements SNAttribute {
	private Double value;
	
	public SNDoubleAttribute(Double value) {
		this.value = value;
	}
	
	@Override
	public SNAttributeType type() {
		return SNAttributeType.DOUBLE;
	}

	@Override
	public Double value() {
		return value;
	}	
}

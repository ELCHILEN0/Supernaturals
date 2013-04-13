package com.TeamNovus.Persistence.Annotations.Relationships;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OneToOne {
	CascadeType cascade() default CascadeType.ALL;
}

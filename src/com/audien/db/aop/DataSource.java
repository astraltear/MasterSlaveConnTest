package com.audien.db.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
	com.audien.db.aop.DataSourceType value() default com.audien.db.aop.DataSourceType.SLAVE;
}

package com.zzq.annotation;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义注解  用来标记 哪个库 哪个表
 */
@Target(METHOD)
@Retention(RUNTIME)
@Inherited
@Documented
public @interface SubDatabaseTable {
    @NotNull
    String dataBaseName();
    @NotNull
    String tableName();
}

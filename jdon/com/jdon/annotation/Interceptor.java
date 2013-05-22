package com.jdon.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Interceptor("aroundAdvice") if there is no any component call this class
 *                              (MethodInterceptor), the MethodInterceptor will
 *                              action for all components called by client (by
 *                              AppUtil.getService or WebApp.getService)
 * 
 * 
 *@Interceptor(name = "myInterceptor", target = "a,c") if there is target
 *                   value, this class will be only available for the target
 *                   component name, not for all components called by client
 * 
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface Interceptor {
	String value() default "";

	String name() default "";

	String pointcut() default "";
}

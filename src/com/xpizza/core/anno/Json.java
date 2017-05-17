package com.xpizza.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 被此注解表明返回给前端时可处理成Json
 */
// @Target({ ElementType.TYPE })
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Json {
}
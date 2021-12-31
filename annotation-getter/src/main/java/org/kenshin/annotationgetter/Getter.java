package org.kenshin.annotationgetter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // 使用在类上
@Retention(RetentionPolicy.SOURCE) //表示这个注解只在编译期起作用
public @interface Getter {
}
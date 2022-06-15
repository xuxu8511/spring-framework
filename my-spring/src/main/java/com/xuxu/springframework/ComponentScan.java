package com.xuxu.springframework;

import java.lang.annotation.*;

/**
 * @auther xuxu
 * @date 2022/6/15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComponentScan {
	//扫描路径
	String value() default "";
}

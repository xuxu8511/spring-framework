package com.xuxu.springframework;

import java.lang.annotation.*;

/**
 * @auther xuxu
 * @date 2022/6/15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
	//bean名称
	String value() default "";
}

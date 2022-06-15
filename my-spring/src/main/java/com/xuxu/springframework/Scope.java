package com.xuxu.springframework;

import java.lang.annotation.*;

/**
 * @auther xuxu
 * @date 2022/6/15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
	BeanDefinition.Scope value() default BeanDefinition.Scope.SCOPE_SINGLE;
}

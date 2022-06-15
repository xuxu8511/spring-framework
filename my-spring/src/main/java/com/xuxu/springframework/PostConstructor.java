package com.xuxu.springframework;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;

/**
 * @auther xuxu
 * @date 2022/6/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PostConstructor {
}

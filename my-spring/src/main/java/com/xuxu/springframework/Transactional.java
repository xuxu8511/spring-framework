package com.xuxu.springframework;

import java.lang.annotation.*;

/**
 * @auther xuxu
 * @date 2022/6/15
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Transactional {
}

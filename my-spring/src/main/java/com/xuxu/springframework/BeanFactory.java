package com.xuxu.springframework;

/**
 * @auther xuxu
 * @date 2022/6/15
 */
public interface BeanFactory {
	Object getBean(String name) throws Exception;

	<T> T getBean(Class<T> requiredType) throws Exception;
}

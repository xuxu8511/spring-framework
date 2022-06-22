package com.xuxu.user;

import com.xuxu.springframework.ApplicationContext;

/**
 * @auther xuxu
 * @date 2022/6/15
 */
public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);

		UserService bean = applicationContext.getBean(UserService.class);
		bean.test();


	}
}

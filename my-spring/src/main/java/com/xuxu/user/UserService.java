package com.xuxu.user;

import com.xuxu.springframework.Autowired;
import com.xuxu.springframework.Component;

/**
 * @auther xuxu
 * @date 2022/6/15
 */
//@Transactional
@Component
public class UserService {

	@Autowired
	private OrderService orderService;

	public void test() {
		System.out.println("这是业务类的test函数调用");
	}
}

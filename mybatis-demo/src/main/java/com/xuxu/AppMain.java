package com.xuxu;

import com.xuxu.bean.XuxuMapper;
import com.xuxu.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xuxu8
 * @date 2022/6/21
 */
public class AppMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		System.out.println("lubanFactoryBean: " + context.getBean("xuXuFactoryBean"));
		System.out.println("lubanFactoryBean: " + context.getBean("&xuXuFactoryBean"));
		System.out.println(context.getBean("&xuXuFactoryBean") instanceof XuxuMapper);
		//System.out.println("lubanFactoryBean: " + context.getBean(XuxuMapper.class));

		System.out.println("this is test demo");
	}
}

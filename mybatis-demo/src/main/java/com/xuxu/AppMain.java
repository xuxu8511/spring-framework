package com.xuxu;

import com.xuxu.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xuxu8
 * @date 2022/6/21
 */

/**
 * 		//userMapper.selectUser();
 * 		String resource = "mybatis-config.xml";
 * 		InputStream inputStream = Resources.getResourceAsStream(resource);
 * 		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); //new DefaultSqlSessionFactory(config);
 * 		SqlSession sqlSession = sqlSessionFactory.openSession(true);
 * 		//通过JDK的动态代理生成UserMapper的代理类
 * 		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
 * 		User user = mapper.selectUser(46L);
 * 		System.out.println(user.toString());
 *
 * 		核心问题：
 *		这里的代理对象UserMapper mapper，怎么注册到Spring容器
 *
 *
 */

public class AppMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		System.out.println("lubanFactoryBean: " + context.getBean("xuXuFactoryBean"));
		System.out.println("lubanFactoryBean: " + context.getBean("&xuXuFactoryBean"));
		//System.out.println(context.getBean("&xuXuFactoryBean") instanceof XuxuMapper);
		//System.out.println("lubanFactoryBean: " + context.getBean(XuxuMapper.class));

		System.out.println("this is test demo222");
	}
}

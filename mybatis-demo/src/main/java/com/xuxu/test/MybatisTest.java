package com.xuxu.test;

import java.io.IOException;

/**
 * @author xuxu8
 * @date 2022/6/20
 */
public class MybatisTest {

	public static void main(String[] args) throws IOException {


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
		 */



	}
}

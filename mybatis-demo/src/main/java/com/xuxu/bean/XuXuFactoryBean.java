package com.xuxu.bean;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuxu
 * @date 2022/6/21
 */
public class XuXuFactoryBean implements FactoryBean {

	private Class mapperInterface;

	public XuXuFactoryBean(Class mapperInterface0) {
		this.mapperInterface = mapperInterface0;
	}

	@Override
	public Object getObject() {

		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return method.invoke(this, args);
			}
		};

		return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, handler);
	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}
}

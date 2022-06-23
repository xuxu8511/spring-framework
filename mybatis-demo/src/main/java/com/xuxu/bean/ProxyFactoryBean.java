package com.xuxu.bean;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuxu
 * @date 2022/6/21
 */
public class ProxyFactoryBean implements FactoryBean {
	private Class mapperInterface;
	public ProxyFactoryBean(Class mapperInterface0) {
		this.mapperInterface = mapperInterface0;
	}
	@Override
	public Object getObject() {
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println(method.getName());
				return null;
			}
		});
	}
	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}
}

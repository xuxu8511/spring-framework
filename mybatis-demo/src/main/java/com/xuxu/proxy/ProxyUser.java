package com.xuxu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xuxu
 * @date 2022/6/23
 */
public class ProxyUser implements InvocationHandler {

	private Object targetObject;

	public ProxyUser(Object targetObject) {
		this.targetObject = targetObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("execute before");
		Object value = method.invoke(targetObject, args);
		System.out.println("execute after");
		return value;
	}

}

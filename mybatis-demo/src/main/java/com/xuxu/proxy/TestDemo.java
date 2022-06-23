package com.xuxu.proxy;

import java.lang.reflect.Proxy;

/**
 * @author xuxu
 * @date 2022/6/23
 */
public class TestDemo {
	public static void main(String[] args) {
		IUser realUser = new RealUser();
		IUser proxyInstance = (IUser) Proxy.newProxyInstance(TestDemo.class.getClassLoader(), new Class[]{IUser.class}, new ProxyUser(realUser));
		proxyInstance.getFlag();
	}
}

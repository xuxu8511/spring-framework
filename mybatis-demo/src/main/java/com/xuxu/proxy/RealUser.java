package com.xuxu.proxy;

/**
 * @author xuxu
 * @date 2022/6/23
 */
public class RealUser implements IUser{
	@Override
	public String getFlag() {
		System.out.println("this is flag");
		return "flag";
	}

	@Override
	public String getUser() {
		System.out.println("this is user");
		return "user";
	}
}

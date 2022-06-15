package com.xuxu.springframework;

/**
 * @auther xuxu
 * @date 2022/6/15
 */

public class BeanDefinition {

	public enum Scope {
		SCOPE_SINGLE,
		SCOPE_PROTOTYPE
	}

	private Object clazzObject;
	private Scope scope;
	private boolean isLazy;

	public BeanDefinition() {
	}

	public BeanDefinition(Object clazzObject, Scope scope, boolean isLazy) {
		this.clazzObject = clazzObject;
		this.scope = scope;
		this.isLazy = isLazy;
	}

	public Object getClazzObject() {
		return clazzObject;
	}

	public void setClazzObject(Object clazzObject) {
		this.clazzObject = clazzObject;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public boolean isLazy() {
		return isLazy;
	}

	public void setLazy(boolean lazy) {
		isLazy = lazy;
	}

}

package com.xuxu.doo;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author xuxu8
 * @date 2022/6/20
 */
@ToString
public class User implements Serializable {
	private int id;
	private String name;
	private String password;

}

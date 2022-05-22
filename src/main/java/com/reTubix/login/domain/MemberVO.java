package com.reTubix.login.domain;

import lombok.Data;

@Data
public class MemberVO {

	private String email;
	
	private String pwd;
	
	private String name;
	
	private String age;
	
	private int point;
	
	private int subs;
	
	private String icon;
	
	private String chimg;
	
	private int state;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getSubs() {
		return subs;
	}

	public void setSubs(int subs) {
		this.subs = subs;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getChimg() {
		return chimg;
	}

	public void setChimg(String chimg) {
		this.chimg = chimg;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
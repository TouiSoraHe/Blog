package com.zzy.user;

import java.io.File;
import java.io.IOException;

public class User {
	private int id;
	
	private String account;
	private String pwd;
	
	public User(String account, String pwd) {
		this.account = account;
		this.pwd = pwd;
	}
	public User() {
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}

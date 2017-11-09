package com.zzy.article;

import java.util.Date;

public class ArticleInfo {
	int id;
	String account;
	String title;
	Date time;
	String startContent;
	public ArticleInfo() {
	}
	public ArticleInfo(String account, String title, Date time, String startContent) {
		super();
		this.account = account;
		this.title = title;
		this.time = time;
		this.startContent = startContent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getStartContent() {
		return startContent;
	}
	public void setStartContent(String startContent) {
		this.startContent = startContent;
	}
}

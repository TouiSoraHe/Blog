package com.zzy.dao;

import com.zzy.user.User;

public interface UserDaoHandler {
	//增
	int add(User u);
	//删
	void delete(User u);
	//改
	void update(User u);
	//查
	User findById(int id);
	User findByAccount(String account);
}

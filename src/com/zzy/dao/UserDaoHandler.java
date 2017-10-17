package com.zzy.dao;

import com.zzy.user.User;

public interface UserDaoHandler {
	//Ôö
	int add(User u);
	//É¾
	void delete(User u);
	//¸Ä
	void update(User u);
	//²é
	User findById(int id);
	User findByAccount(String account);
}

package com.zzy.dao;

import com.zzy.user.User;

public interface UserDaoHandler {
	//��
	int add(User u);
	//ɾ
	void delete(User u);
	//��
	void update(User u);
	//��
	User findById(int id);
	User findByAccount(String account);
}

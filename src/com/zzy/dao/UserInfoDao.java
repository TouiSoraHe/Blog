package com.zzy.dao;

import com.zzy.user.UserInfo;

public interface UserInfoDao {
		//增
		boolean add(UserInfo u);
		//删
		boolean delete(UserInfo u);
		//改
		boolean update(UserInfo u);
		//查
		UserInfo findByAccount(String account);
}

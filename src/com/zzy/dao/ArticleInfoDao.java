package com.zzy.dao;

import java.util.List;

import com.zzy.article.ArticleInfo;

public interface ArticleInfoDao {
	//增
	boolean add(ArticleInfo a);

	//删
	boolean delete(ArticleInfo a);

	//改
	boolean update(ArticleInfo a);

	//查
	List<ArticleInfo> findByAccount(String account);
}

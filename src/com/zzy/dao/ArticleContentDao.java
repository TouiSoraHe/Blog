package com.zzy.dao;

import com.zzy.article.ArticleContent;

public interface ArticleContentDao {
		//增
		boolean add(ArticleContent a);

		//删
		boolean delete(ArticleContent a);

		//改
		boolean update(ArticleContent a);

		//查
		ArticleContent findById(int id);

}

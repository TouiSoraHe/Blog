package com.zzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zzy.article.ArticleContent;
import com.zzy.article.ArticleInfo;
import com.zzy.util.DBUtil;

public class ArticleContentDaoImp implements ArticleContentDao {

	@Override
	public boolean add(ArticleContent a) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement stat = null;
		String sql = "insert into articleContent (id,content) values(?,?)";
		try {
			stat = conn.prepareStatement(sql);
			stat.setInt(1, a.getId());
			stat.setString(2, a.getContent());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.close(null, stat, conn);
		}
		return true;
	}

	@Override
	public boolean delete(ArticleContent a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ArticleContent a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArticleContent findById(int id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArticleContent articContent=null;
		String sql = "select content from articlecontent where id=?";
		try {
			stat = conn.prepareStatement(sql);
			stat.setInt(1, id);
			rs = stat.executeQuery();
			if (rs.next()) {
				articContent = new ArticleContent();
				articContent.setId(id);
				articContent.setContent(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, stat, conn);
		}
		return articContent;
	}

}

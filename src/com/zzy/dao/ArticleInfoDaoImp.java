package com.zzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zzy.article.ArticleInfo;
import com.zzy.util.DBUtil;

public class ArticleInfoDaoImp implements ArticleInfoDao {

	@Override
	public boolean add(ArticleInfo a) {
		String account = a.getAccount();
		Connection conn = DBUtil.getConnection();
		PreparedStatement stat = null;
		String sql = "insert into articleInfo (account,title,time,start) values(?,?,?,?)";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, account);
			stat.setString(2, a.getTitle());
			stat.setTimestamp(3, new Timestamp(a.getTime().getTime()));
			stat.setString(4, a.getStartContent());
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
	public boolean delete(ArticleInfo a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ArticleInfo a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ArticleInfo> findByAccount(String account) {
		List<ArticleInfo> articleInfos = new ArrayList<ArticleInfo>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement stat = null;
		ResultSet rs=null;
		String sql = "select * from articleInfo where account=?";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, account);
			 rs= stat.executeQuery();
			 while(rs.next())
			 {
				 ArticleInfo articleInfo=new ArticleInfo();
				 articleInfo.setId(rs.getInt(1)); 
				 articleInfo.setAccount(rs.getString(2));
				 articleInfo.setTitle(rs.getString(3));
				 articleInfo.setTime(rs.getTimestamp(4));
				 articleInfo.setStartContent(rs.getString(5));
				 articleInfos.add(articleInfo);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, stat, conn);
		}
		return articleInfos;
	}

}

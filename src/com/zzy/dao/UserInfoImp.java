package com.zzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zzy.user.UserInfo;
import com.zzy.util.DBUtil;

public class UserInfoImp implements UserInfoDao {

	@Override
	public boolean add(UserInfo u) {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtil.getConnection();
			String account = u.getAccount();
			String name = u.getName();
			int age = u.getAge();
			String intro = u.getIntro();
			String gender = u.getGender();
			String sql = "insert into userinfo value(?,?,?,?,?)";
			stat = conn.prepareStatement(sql);
			stat.setString(1, account);
			stat.setString(2, name);
			stat.setString(3, gender);
			stat.setInt(4, age);
			stat.setString(5, intro);
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.close(null, stat, conn);
		}
		return true;
	}

	@Override
	public boolean delete(UserInfo u) {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtil.getConnection();
			String account=u.getAccount();
			String sql = "delete from userinfo where account=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, account);
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.close(null, stat, conn);
		}
		return true;
	}

	@Override
	public boolean update(UserInfo u) {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update userinfo set name=?,gender=?,age=?,intro=? where account=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, u.getName());
			stat.setString(2, u.getGender());
			stat.setInt(3, u.getAge());
			stat.setString(4,u.getIntro());
			stat.setString(5, u.getAccount());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.close(null, stat, conn);
		}
		return true;
	}

	@Override
	public UserInfo findByAccount(String account) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from userinfo where account=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, account);
			rs = stat.executeQuery();
			if(rs.next())
			{
				UserInfo userinfo = new UserInfo();
				userinfo.setAccount(account);
				userinfo.setName(rs.getString(2));
				userinfo.setGender(rs.getString(3));
				userinfo.setAge(rs.getInt(4));
				userinfo.setIntro(rs.getString(5));
				return userinfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(rs, stat, conn);
		}
		return null;
	}

}

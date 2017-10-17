package com.zzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zzy.user.User;
import com.zzy.util.DBUtil;

public class UserDaoExam implements UserDaoHandler {

	/**
	 * 添加一个用户到数据表中
	 * @param u 将要添加的用户
	 * @return 添加成功则返回该用户在数据表中的id值，添加失败或者账号已经存在则返回-1
	 */
	public int add(User u) {
		//如果账号已经存在
		if (findByAccount(u.getAccount()) != null) {
			return -1;
		}
		String account = u.getAccount();
		String pwd = u.getPwd();
		int id = -1;
		Connection conn = DBUtil.getConnection();
		PreparedStatement stat = null;
		String sql = "insert into user (account,password) values(?,?)";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, account);
			stat.setString(2, pwd);
			stat.executeUpdate();
			id = findByAccount(account).getId();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, stat, conn);
		}
		return id;
	}

	/**
	 * 从数据表中删除一条数据
	 * @param u 要从数据表中删除的数据对象，注意:该函数依赖于user对象中的id属性，所以参数u的id不能为空
	 */
	public void delete(User u) {
		int id = u.getId();
		Connection conn = DBUtil.getConnection();
		String sql = "delete from user where id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, conn);
		}
	}
	/**
	 * 更新数据到数据表中，在数据表中查找参数u的id值，找到则将参数u的其余属性更新到数据表中
	 * @param u 要更新的对象
	 */
	public void update(User u) {
		Connection conn = DBUtil.getConnection();
		String sql = "update user set account=? , password=? where id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, u.getAccount());
			ps.setString(2, u.getPwd());
			ps.setInt(3, u.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, ps, conn);
		}

	}

	/**
	 * 查找一个用户
	 * @param id 要查找的用户的id值
	 * @return 返回查找到的对象，找不到则返回null
	 */
	public User findById(int id) {
		User ret = null;
		Connection conn = DBUtil.getConnection();
		String sql = "select * from user where id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			rs = ps.executeQuery();
			if (rs.next()) {
				ret = new User();
				ret.setId(id);
				ret.setAccount(rs.getString(2));
				ret.setPwd(rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return ret;
	}

	/**
	 * 查找一个用户
	 * @param account 要查找的用户的account
	 * @return 返回查找到的对象，找不到则返回null
	 */
	public User findByAccount(String account) {
		User ret = null;
		Connection conn = DBUtil.getConnection();
		String sql = "select * from user where account=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			ps.execute();
			rs = ps.executeQuery();
			if (rs.next()) {
				ret = new User();
				ret.setId(rs.getInt(1));
				ret.setAccount(account);
				ret.setPwd(rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return ret;
	}

}

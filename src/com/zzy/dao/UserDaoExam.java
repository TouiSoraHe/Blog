package com.zzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zzy.user.User;
import com.zzy.util.DBUtil;

public class UserDaoExam implements UserDaoHandler {

	/**
	 * ���һ���û������ݱ���
	 * @param u ��Ҫ��ӵ��û�
	 * @return ��ӳɹ��򷵻ظ��û������ݱ��е�idֵ�����ʧ�ܻ����˺��Ѿ������򷵻�-1
	 */
	public int add(User u) {
		//����˺��Ѿ�����
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
	 * �����ݱ���ɾ��һ������
	 * @param u Ҫ�����ݱ���ɾ�������ݶ���ע��:�ú���������user�����е�id���ԣ����Բ���u��id����Ϊ��
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
	 * �������ݵ����ݱ��У������ݱ��в��Ҳ���u��idֵ���ҵ��򽫲���u���������Ը��µ����ݱ���
	 * @param u Ҫ���µĶ���
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
	 * ����һ���û�
	 * @param id Ҫ���ҵ��û���idֵ
	 * @return ���ز��ҵ��Ķ����Ҳ����򷵻�null
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
	 * ����һ���û�
	 * @param account Ҫ���ҵ��û���account
	 * @return ���ز��ҵ��Ķ����Ҳ����򷵻�null
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

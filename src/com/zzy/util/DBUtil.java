package com.zzy.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBUtil {
	private static String URL;
	private static String USER;
	private static String PASSWD;
	
	private DBUtil() {}
	
	static {
		Properties p=new Properties();
		String configFilePath = DBUtil.class.getResource("/").getFile().replaceAll("%20", " ");
		configFilePath=configFilePath.substring(1,configFilePath.lastIndexOf("/WEB-INF"))+"/config/dbconf.properties";
		try {
			p.load(new FileInputStream(configFilePath));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		URL=p.getProperty("jdbc.url");
		USER=p.getProperty("jdbc.username");
		PASSWD=p.getProperty("jdbc.password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		Connection conn = null;
		try {
			conn=DriverManager.getConnection(URL, USER, PASSWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(ResultSet rs,Statement stat,Connection conn)
	{
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		}
		if(stat!=null)
		{
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stat=null;
		}
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
}

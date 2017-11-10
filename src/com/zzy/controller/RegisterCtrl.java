package com.zzy.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.dao.UserDaoImp;
import com.zzy.dao.UserInfoImp;
import com.zzy.user.User;
import com.zzy.user.UserInfo;

/**
 * @version 创建时间：2017年10月25日  上午10:15:53
 * tags
 */
@WebServlet("/Servlet/RegisterCtrl")
public class RegisterCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("repassword");
		UserDaoImp ude = new UserDaoImp();
		User user = new User(account, password);
		StringBuilder message = new StringBuilder();
		if(!isOk(message, account, password, rePassword,ude,user))
		{
			request.getRequestDispatcher("/register.jsp"+message.toString()).forward(request, response);
			return;
		}
		UserInfoImp userInfoImp = new UserInfoImp();
		UserInfo userInfo = new UserInfo();
		userInfo.setAccount(account);
		userInfo.setName(account);
		userInfoImp.add(userInfo);
		message.append("?message=注册成功,立即登录吧！");
		request.getRequestDispatcher("/Login.jsp"+message).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private boolean isOk(StringBuilder message, String account, String password, String rePassword,
			UserDaoImp ude,User user) {
		if (account == null || account.equals("")) {
			message.append("?message=账号不能为空");
			return false;
		} else if (password == null || "".equals(password)) {
			message.append("?message=密码不能为空&account=" + account);
			return false;
		} else if (rePassword == null || "".equals(rePassword) || !password.equals(rePassword)) {
			message.append("?message=两次密码不一致&account=" + account);
			return false;
		}
		if (ude.add(user) == -1) {
			message.append("?message=该账号已经存在&account=" + account);
			return false;
		}
		return true;
	}
}
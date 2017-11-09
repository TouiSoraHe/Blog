package com.zzy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.article.ArticleInfo;
import com.zzy.dao.ArticleInfoDaoImp;
import com.zzy.dao.UserDaoImp;
import com.zzy.dao.UserInfoImp;
import com.zzy.user.User;
import com.zzy.user.UserInfo;

/**
 * @version 创建时间：2017年10月17日  上午9:42:14
 * tags
 */
@WebServlet("/Servlet/LoginCtrl")
public class LoginCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String account = request.getParameter("account");
		String pwd = request.getParameter("password");
		String autologin = request.getParameter("autologin");
		if (account == null || account.equals("")) {
			request.getRequestDispatcher("/Login.jsp?message=账号不能为空").forward(request, response);
			return;
		} else if (pwd == null || "".equals(pwd)) {
			request.getRequestDispatcher("/Login.jsp?message=密码不能为空&account=" + account).forward(request, response);
			return;
		}
		UserDaoImp ude = new UserDaoImp();
		User user = ude.findByAccount(account);
		if (user == null) {
			request.getRequestDispatcher("/Login.jsp?message=账号不存在&account=" + account).forward(request, response);
		} else if (!user.getPwd().equals(pwd)) {
			request.getRequestDispatcher("/Login.jsp?message=密码错误&account=" + account).forward(request, response);
		} else {
			//允许登录
			request.getSession().setAttribute("user", user);
			UserInfo userInfo=new UserInfoImp().findByAccount(user.getAccount());
			List<ArticleInfo> articleInfos = new ArticleInfoDaoImp().findByAccount(user.getAccount());
			request.getSession().setAttribute("userInfo", userInfo);
			request.getSession().setAttribute("articleInfos", articleInfos);
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("JSESSIONID")) {
					if ("on".equals(autologin)) {
						cookie.setMaxAge(604800);
						cookie.setPath("/Blog");
						cookie.setHttpOnly(true);
						response.addCookie(cookie);
					}
					else
					{
						cookie.setMaxAge(-1);
						cookie.setPath("/Blog");
						cookie.setHttpOnly(true);
						response.addCookie(cookie);
					}
				}
			}
			response.sendRedirect("/Blog/BlogIndex.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
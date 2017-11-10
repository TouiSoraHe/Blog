package com.zzy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.dao.UserInfoImp;
import com.zzy.user.UserInfo;

/**
 * @version 创建时间：2017年11月7日  下午6:41:11
 * tags
 */
@WebServlet("/Servlet/UpdateUserInfoCtrl")
public class UpdateUserInfoCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String name = request.getParameter("name");
		userInfo.setName((name == null || "".equals(name)) ? userInfo.getAccount() : name);
		int age = 0;
		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch (NumberFormatException e) {
			age = 0;
		}
		userInfo.setAge(age);
		userInfo.setGender(request.getParameter("gender") == null ? "未知" : request.getParameter("gender"));
		userInfo.setIntro(request.getParameter("intro"));
		UserInfoImp userInfoImp = new UserInfoImp();
		userInfoImp.update(userInfo);
		request.getSession().setAttribute("userInfo", userInfo);
		request.getRequestDispatcher("/userinfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
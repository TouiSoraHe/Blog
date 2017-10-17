package com.zzy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.dao.UserDaoExam;
import com.zzy.user.User;

/**
 * @version 创建时间：2017年10月17日  上午9:42:14
 * tags
 */
@WebServlet("/LoginCtrl")
public class LoginCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String account=request.getParameter("account");
		String pwd = request.getParameter("password");
		UserDaoExam ude = new UserDaoExam();
		User ret=ude.findByAccount(account);
		if(ret==null)
		{
			request.getRequestDispatcher("/Login?message=账号不存在").forward(request, response);
		}
		else if(!ret.getPwd().equals(pwd))
		{
			request.getRequestDispatcher("/Login?message=密码错误").forward(request, response);
		}
		else
		{
			//允许登录
			response.getWriter().println("成功登录");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
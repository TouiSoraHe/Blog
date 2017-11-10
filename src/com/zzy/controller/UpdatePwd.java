package com.zzy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.dao.UserDaoImp;
import com.zzy.user.User;

/**
 * @version 创建时间：2017年11月7日  下午7:39:37
 * tags
 */
@WebServlet("/Servlet/UpdatePwd")
public class UpdatePwd extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String oldPwd = request.getParameter("oldPassword");
		String newPwd = request.getParameter("newPassword");
		String newRePwd = request.getParameter("newRePassword");
		User user = (User) request.getSession().getAttribute("user");
		StringBuilder message = new StringBuilder();
		if(!isOk(message, oldPwd, newPwd, newRePwd, user)) 
		{
			request.getRequestDispatcher("/updatepwd.jsp"+message).forward(request, response);
			return;
		}
		UserDaoImp ude = new UserDaoImp();
		user.setPwd(newPwd);
		ude.update(user);
		request.getSession().setAttribute("user", user);
		message.append("?message=密码修改成功");
		request.getRequestDispatcher("/message.jsp"+message).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean isOk(StringBuilder message,String oldPwd,String newPwd,String newRePwd,User user)
	{
		if(oldPwd==null || "".equals(oldPwd))
		{
			message.append("?message=原密码不能为空");
			return false;
		}
		else if(newPwd==null || "".equals(newPwd))
		{
			message.append("?message=新密码不能为空");
			return false;
		}
		else if(newRePwd==null || "".equals(newRePwd))
		{
			message.append("?message=再次输入不能为空");
			return false;
		}
		else if(!newRePwd.equals(newPwd))
		{
			message.append("?message=两次密码不一致");
			return false;
		}
		else if(!oldPwd.equals(user.getPwd()))
		{
			message.append("?message=原密码错误");
			return false;
		}
		return true;
	}
}
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
 * @version ����ʱ�䣺2017��10��17��  ����9:42:14
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
			request.getRequestDispatcher("/Login?message=�˺Ų�����").forward(request, response);
		}
		else if(!ret.getPwd().equals(pwd))
		{
			request.getRequestDispatcher("/Login?message=�������").forward(request, response);
		}
		else
		{
			//�����¼
			response.getWriter().println("�ɹ���¼");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
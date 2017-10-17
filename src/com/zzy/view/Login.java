package com.zzy.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 创建时间：2017年10月17日  下午7:40:40
 * tags
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String ret = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<title>Blog</title>\r\n" + 
				"<link href=\"css/style.css\" rel='stylesheet' type='text/css' />\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"<script type=\"application/x-javascript\"> addEventListener(\"load\", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>\r\n" + 
				"<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700,800' rel='stylesheet' type='text/css'>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<h1>个人博客</h1>\r\n" + 
				"<div class=\"app-timer\">\r\n" + 
				"<div class=\"timer\"><img src=\"images/timer.png\" class=\"img-responsive\" alt=\"\" /></div>\r\n" + 
				"<form action='/Blog/LoginCtrl' method='post'>\r\n" + 
				"<ul>\r\n" + 
				"<li>\r\n" + 
				"<a href=\"#\" class=\" icon email\"></a>\r\n" + 
				"<input type=\"text\" name='account' class=\"text\" placeholder='请输入账号' >\r\n" + 
				"</li>\r\n" + 
				"<li>\r\n" + 
				"<a href=\"#\" class=\" icon lock\"></a>\r\n" + 
				"<input type=\"password\" name='password' placeholder='请输入密码'>\r\n" + 
				"</li>\r\n" + 
				"<div class=\"clear\"></div>\r\n" + 
				"\r\n" + 
				"<div class=\"submit\"><input type=\"submit\" value=\"登录\" ></div>\r\n" + 
				"<div class=\"clear\"></div>\r\n" + 
				"<p><a href=\"#\">忘记密码 ?</a></p>\r\n" + 
				"</ul>\r\n" + 
				"</form>\r\n" + 
				"<div class=\"whyt\"><p>新用户? <a href=\"#\">注册</a></p></div>\r\n" + 
				"</div>";
		String message = request.getParameter("message");
		ret=ret+"<script> alert(\""+message+"\")</script>"+"</body>\r\n</html>";
		response.getWriter().println(ret);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
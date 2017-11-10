package com.zzy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns= {"/Servlet/*"})
public class ServletFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpServletRequest r = (HttpServletRequest) request;
		if(r.getSession().getAttribute("user")==null && r.getRequestURI().lastIndexOf("/Blog/Servlet/LoginCtrl")==-1 && r.getRequestURI().lastIndexOf("/Blog/Servlet/RegisterCtrl")==-1)
		{
			request.getRequestDispatcher("/Login.jsp?message=请先登录").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

}

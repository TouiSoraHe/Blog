package com.zzy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(urlPatterns= {"*.jsp"})
public class JspFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpServletRequest r = (HttpServletRequest) request;
		String uri = r.getRequestURI();
		if("/Blog/Login.jsp".equals(uri) || "/Blog/register.jsp".equals(uri) || r.getSession().getAttribute("user")!=null)
		{
			chain.doFilter(request, response);
		}
		else
		{
			r.getRequestDispatcher("/Login.jsp?message=请先登录").forward(request, response);
		}
	}
}

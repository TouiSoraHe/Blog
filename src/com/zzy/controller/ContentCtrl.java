package com.zzy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.article.ArticleContent;
import com.zzy.article.ArticleInfo;
import com.zzy.dao.ArticleContentDaoImp;

/**
 * @version 创建时间：2017年11月9日  下午5:37:47
 * tags
 */
@WebServlet("/Servlet/ContentCtrl")
public class ContentCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		List<ArticleInfo> articleInfos = (List<ArticleInfo>) request.getSession().getAttribute("articleInfos");
		ArticleInfo articleInfo = null;
		if (request.getParameter("id") == null) {
			request.getRequestDispatcher("/message.jsp?message=页面不存在").forward(request, response);
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/message.jsp?message=错误的ID号").forward(request, response);
			return;
		}
		for (ArticleInfo aInfo : articleInfos) {
			if (aInfo.getId() == id) {
				articleInfo = aInfo;
			}
		}
		ArticleContent articleContent = new ArticleContentDaoImp().findById(id);
		if (articleInfo == null || articleContent == null) {
			request.getRequestDispatcher("/message.jsp?message=页面不存在").forward(request, response);
			return;
		}
		request.setAttribute("articleInfo", articleInfo);
		request.setAttribute("articleContent", articleContent);
		request.getRequestDispatcher("/article.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
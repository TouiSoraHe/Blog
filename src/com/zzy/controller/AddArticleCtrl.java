package com.zzy.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.article.ArticleContent;
import com.zzy.article.ArticleInfo;
import com.zzy.dao.ArticleContentDao;
import com.zzy.dao.ArticleContentDaoImp;
import com.zzy.dao.ArticleInfoDao;
import com.zzy.dao.ArticleInfoDaoImp;
import com.zzy.user.UserInfo;

/**
 * @version 创建时间：2017年11月9日  下午5:12:29
 * tags
 */
@WebServlet("/Servlet/AddArticleCtrl")
public class AddArticleCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ArticleContentDao articleContentDaoImp = new ArticleContentDaoImp();
		ArticleInfoDao articleInfoDao = new ArticleInfoDaoImp();
		UserInfo userInfo =(UserInfo) request.getSession().getAttribute("userInfo");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		if(title==null || "".equals(title))
		{
			if(content!=null && !content.equals(""))
			{
				request.setAttribute("content", content);
			}
			request.getRequestDispatcher("/addArticle.jsp?message=标题不能为空").forward(request, response);
			return;
		}
		if(content==null||"".equals(content))
		{
			request.setAttribute("title", title);
			request.getRequestDispatcher("/addArticle.jsp?message=内容不能为空").forward(request, response);
			return;
		}
		articleInfoDao.add(new ArticleInfo(userInfo.getAccount(), title, new Date(), (content.length()>146?
				content.substring(0, 146):content)+"......"));
		List<ArticleInfo> articleInfos = articleInfoDao.findByAccount(userInfo.getAccount());
		for (ArticleInfo articleInfo : articleInfos) {
			if(articleInfo.getTitle().equals(title))
			{
				ArticleContent a = new ArticleContent();
				a.setId(articleInfo.getId());
				a.setContent(content);
				articleContentDaoImp.add(a);
			}
		}
		request.getSession().setAttribute("articleInfos", articleInfos);
		request.getRequestDispatcher("/message.jsp?message=添加博客成功").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
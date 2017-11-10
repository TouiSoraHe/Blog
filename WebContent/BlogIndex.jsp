<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.zzy.user.*,com.zzy.article.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="https://cdn.bootcss.com/normalize/7.0.0/normalize.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="/Blog/css/BlogCss.css">
<title>个人首页</title>
</head>
<body>
	<%
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		List<ArticleInfo> arlicleInfos = (List<ArticleInfo>) request.getSession().getAttribute("articleInfos");
	%>
	<div class="side-bar">
		<div class="header">
			<a href="/Blog/BlogIndex.jsp" class="logo"><%=userInfo.getName()%></a>
			<div class="intro"><%=userInfo.getIntro()%></div>
		</div>
		<div class="nav">
			<a href="/Blog/BlogIndex.jsp" class="item">首页</a> 
			<a href="/Blog/userinfo.jsp" class="item">个人信息</a>
		</div>
		<div class="tag-list">
			<a href="/Blog/addArticle.jsp" class="item">新建博客</a>
			<a href="/Blog/updateuserinfo.jsp" class="item">修改个人信息</a> 
			<a href="/Blog/updatepwd.jsp" class="item">修改密码</a>
			<a href="/Blog/Servlet/SignOutCtrl" class="item">退出登录</a>
		</div>
	</div>
	<div class="main">
		<div class="article-list">
		<%
		for(ArticleInfo articleInfo : arlicleInfos)
		{
			out.println("<div class='item'>");
			out.println("<a href='/Blog/Servlet/ContentCtrl?id="+articleInfo.getId()+"' class='title'>"+articleInfo.getTitle()+"</a>");
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss");
			out.println("<div class='status'>"+dateFm.format(articleInfo.getTime())+"</div>");
			out.println("<div class='content'>"+articleInfo.getStartContent()+"</div>");
			out.println("</div>");
		}
		%>
		</div>
	</div>
</body>
</html>
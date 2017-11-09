<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.zzy.user.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="https://cdn.bootcss.com/normalize/7.0.0/normalize.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="/Blog/css/BlogCss.css">
<title>新建博客</title>
</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/Blog/Login.jsp");
			return;
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String message = request.getParameter("message");
		message = message == null ? "" : message;
		String title =(String) request.getAttribute("title");
		String content =(String) request.getAttribute("content");
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
		<div class="userinfo">
			<div class="title">个人信息</div>
			<hr>
			<form class="content" action="/Blog/Servlet/AddArticleCtrl"
				method="post">
				<div class="item"><input type="text" name='title' <%= title!=null?"value="+title:""%> placeholder='在此输入您的标题'></div>
				<div class="item"><textarea name="content" id="" cols="100%" rows="30" placeholder='在此输入您的内容'><%= content==null?"":content %></textarea></div>
				<div class="message"><%= message%></div>
				<input type="submit" class="save" value="保存">
			</form>
			<div class="bottom"></div>
		</div>
	</div>
</body>
</html>
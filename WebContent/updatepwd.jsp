<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.zzy.user.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="https://cdn.bootcss.com/normalize/7.0.0/normalize.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="/Blog/css/BlogCss.css">
<title>个人信息修改</title>
</head>
<body>
	<%
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String message = request.getParameter("message");
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
			<div class="title">修改密码</div>
			<hr>
			<form class="content" action="/Blog/Servlet/UpdatePwd"
				method="post">
				<div class="item">
					账号：<%=userInfo.getAccount()%></div>
				<div class="item">
					原密码：<input type="password" name="oldPassword" >
				</div>
				<div class="item">
					新密码：<input type="password" name="newPassword">
				</div>
				<div class="item">
					再输一次：<input type="password" name="newRePassword">
				</div>
				<div class="message"><%= message==null?"":message %></div>
				<input type="submit" class="save" value="保存">
			</form>
			<div class="bottom"></div>
		</div>
	</div>
</body>
</html>
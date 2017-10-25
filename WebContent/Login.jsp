<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/common.css" />
	<title>个人博客登录</title>
</head>
<body>
<%
	String m=request.getParameter("message");
	String account=request.getParameter("account");
	if(account==null)
	{
		account="";
	}
	if(m==null)
	{
		m="";
	}
%>
	<div class="wrap login_wrap">
		<div class="content">

			<div class="logo"></div>

			<div class="login_box">	

				<div class="login_form">
					<div class="login_title">
						登录
					</div>
					<form action="/Blog/LoginCtrl" method="post">
						<div class="form_text_ipt">
							<input name="account" type="text" placeholder="账号" value=<%=account%>>
						</div>
						<div class="form_text_ipt">
							<input name="password" type="password" placeholder="密码">
						</div>
						<div class="form_check_ipt">
							<div class="left check_left">
								<label><input name="autologin" type="checkbox"> 下次自动登录</label>
							</div>
							<div class="right check_right">
								<a href="#">忘记密码</a>
							</div>
						</div>
						<div class="form_btn">
							<button type="submit">登录</button>
						</div>
						<div class="form_reg_btn" style="color:#f60"><%=m%></div>
						<div class="form_reg_btn" style="padding:10px" >
							<span>还没有帐号？</span><a href="register.jsp">马上注册</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
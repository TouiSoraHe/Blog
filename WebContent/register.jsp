<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人博客注册</title>
<link rel="stylesheet" href="/Blog/css/reset.css" />
<link rel="stylesheet" href="/Blog/css/common.css" />
</head>
<body>
<%
	String message=request.getParameter("message");
	String account=request.getParameter("account");
	if(account==null)
	{
		account="";
	}
	if(message==null)
	{
		message="";
	}
%>
		<div class="wrap login_wrap">
			<div class="content">
				
				<div class="logo"></div>
				
				<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">
							注册
						</div>
						<form action="/Blog/Servlet/RegisterCtrl" method="post">
							
							<div class="form_text_ipt">
								<input name="account" type="text" placeholder="账号" value=<%=account %>>
							</div>
							<div class="form_text_ipt">
								<input name="password" type="password" placeholder="密码">
							</div>
							<div class="form_text_ipt">
								<input name="repassword" type="password" placeholder="重复密码">
							</div>

							<div class="form_btn">
								<button type="submit">注册</button>
							</div>
							<div class="form_reg_btn" style="color:#f60"><%=message%></div>
							<div class="form_reg_btn" style="padding:10px">
								<span>已有帐号？</span><a href="/Blog/Login.jsp">马上登录</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
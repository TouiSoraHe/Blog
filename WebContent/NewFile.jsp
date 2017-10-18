<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/Blog/NewFile.jsp" method='post'>
		<input type="text" name='num1'>
		<br>
		<input type='text' name='num2'>
		<br>
		<select name='oper'>
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">*</option>
			<option value="/">/</option>
		</select>
		<br>
		<input type="submit" value='提交'>
	</form>
	<br>
	
	<% 
	
	String ret = (String) request.getAttribute("ret");
	if(ret!=null)
	{
		out.println("结果是:" + ret);
		return;
	}
	String num1 = request.getParameter("num1");
	String num2= request.getParameter("num2");
	String oper = request.getParameter("oper");
	if(num1==null || num2==null || oper==null)
		return;
	ret = "";
	switch(oper)
	{
	case "+":ret=String.valueOf(Double.parseDouble(num1)+Double.parseDouble(num2)); break;
	case "-":ret=String.valueOf(Double.parseDouble(num1)-Double.parseDouble(num2));break;
	case "*":ret=String.valueOf(Double.parseDouble(num1)*Double.parseDouble(num2));break;
	case "/":ret=String.valueOf(Double.parseDouble(num1)/Double.parseDouble(num2));break;
	}
	request.setAttribute("ret", ret);
	request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
	%>
	
</body>
</html>
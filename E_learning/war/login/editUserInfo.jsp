<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*" %>
<%@ page import="fan.gae.learning.user.UserInfo"%>
<%@ page import="fan.gae.learning.user.ConcreteUserFactory" %>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		ConcreteUserFactory uf = new ConcreteUserFactory();
		//UserInfo userInfo = new UserInfo();
		if (user == null) {
			response.sendRedirect("/");
		} else {
			UserInfo userInfo = uf.getUser(user.getUserId()).get(0);
			
	%>

	<h1>个人资料设置</h1>
	<div class="edit_item">
	<form action="/editUserInfo" method="post">
		用户名：<input type="text" id="username" name="username" value="<%=userInfo.getUserName() %>" /><br>
		密码：<input type="password" id="password" name="password" value="<%=userInfo.getPassword() %>" /><br>
		邮箱:<input type="email" id="email" name="email" value="<%=userInfo.getEmail() %>" /><br>
		<input type="submit" name="submit" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" name="reset" value="取消">
		</form>
	</div>
	<%} %>
</body>
</html>
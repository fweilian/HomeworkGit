<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
	
		if(user == null) {
			response.sendRedirect("/discuss/group.jsp");
		} else {
	%>
		<form action="/createGroup" method="post">
			小组名称 <br><input type="text" name="group_name" value="" /><br>
			小组类别<br><input type="text" name="group_type" value="" /><br>
			小组简介<br><textarea rows="5" cols="20" name="group_introduce"></textarea><br>
			<input type="submit" name="申请">
		</form>
	<% 
		}
	%>
</body>
</html>
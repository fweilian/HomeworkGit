<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="fan.gae.learning.user.*" %>
	<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user == null) {
			response.sendRedirect("/");
		} else {
			String userId = request.getParameter("userId");
			System.out.println("useriD" + userId);
			ConcreteUserFactory cf = new ConcreteUserFactory();
			UserInfo userInfo = cf.getUser(userId).get(0);
			int role = userInfo.getRole();
			switch (role) {
			case 0:
				response.sendRedirect("/");
				break;
			case 1:
				response.sendRedirect("/login/userInfo.jsp");
				break;
			case 2:
				response.sendRedirect("/login/teacherInfo.jsp");
				break;
			case 3:
				response.sendRedirect("/login/adminInfo.jsp");
				break;
			default:
				response.sendRedirect("/");
			}
		}
	%>
</body>
</html>
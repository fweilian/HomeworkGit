<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>

<%@ page import="fan.gae.learning.user.*"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/css/home.css">
<title>Insert title here</title>
</head>
<body>
	<%
		System.out.println("creating new user ");
		User user = (User) request.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}

		String username = request.getParameter("nickname");
		String password = request.getParameter("password");
		String aff_password = request.getParameter("affirm_password");
		String email = request.getParameter("email");

		//Factory factory = Factory.getInstance();
		//UserInfo user = factory.getUser();
		//UserFactory userFactory = factory.getUserFactory();

		//UserService userService = UserServiceFactory.getUserService();
		//User user = userService.getCurrentUser();

		//UserInfo user = new UserInfo();
		ConcreteUserFactory userFactory = new ConcreteUserFactory();
		//user = userFactory.isCheck(username);
		boolean flag = true;//userFactory.isCheck(username);
		if (flag) {
			//user.getUserId();
			//user.setUserName(username);
			//user.setPassword(password);
			//user.setEmail(email);

			boolean state = userFactory.AddUser(user.getUserId(), username,
					password, 1, email, new Date(), 1); //learning 1
			if (state == true) {
				out.println("<div><a href='/login/register.jsp'>home</a></div>");
			} else {
				out.println("Register fail! ");
			}
		} else {
			out.println("<div>Again<a href='login/register.jsp'></a></div>");
		}
	%>
</body>
</html>
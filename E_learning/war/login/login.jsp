<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%
	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();
	String url = userService.createLogoutURL("/");
	out.print("<a  href='/'>E_learning</a>&nbsp;&nbsp;&nbsp;");
	out.print("<a href='/'>首页</a>&nbsp;&nbsp;&nbsp;");
	out.print("<a href='/find/findCourse.jsp'>课程</a>&nbsp;&nbsp;&nbsp;");
	out.print("<a href='/discuss/group.jsp'>论坛</a>&nbsp;&nbsp;&nbsp;");
	out.print("<a href='"+url+"'>退出</a>");
%>

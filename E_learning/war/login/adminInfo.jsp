<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="fan.gae.learning.user.*"%>
<%@ page import="fan.gae.learning.course.*" %>
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
		ConcreteUserFactory uf = new ConcreteUserFactory();
		if(user == null) {
			response.sendRedirect("/");
		} else {
			UserInfo adminInfo = uf.getUser(user.getUserId()).get(0);
	%>
	<center>
		<h3>管理员</h3>
	</center>
	<div>
		<a href="adminInfo.jsp?type_value=pass">已审核</a>
		<a href="adminInfo.jsp?type_value=unPass">未审核</a>
		<br>
	</div>
	<table width=80%>
		<tr>
			<td>课程名</td>
			<td>所有者</td>
			<td>课程类型</td>
			<td>审核</td>
		</tr>

		<%
			String type = request.getParameter("type_value");
			
			if(type==null) {
				type="pass";
			}
			ConcreteCourseFactory cf = new ConcreteCourseFactory();
			ConcreteTeacherFactory tf = new ConcreteTeacherFactory();
			List<CourseInfo> courseInfos = cf.getCourseFromPass(type);
			
			for(CourseInfo courseInfo : courseInfos) {
				TeacherInfo teacherInfo = tf.getTeacher(courseInfo.getTeacherId()).get(0);
				out.print("<tr>");
				out.print("<td>" + courseInfo.getCourseName() + "</td>");
				out.print("<td>" + teacherInfo.getRealName() + "</td>");
				out.print("<td>" + courseInfo.getCourseType() + "</td>");
				out.print("<td><a href='/admin/examine.jsp?teacher_id=" +courseInfo.getTeacherId()+"&course_name="+courseInfo.getCourseName()+"'>"+ (courseInfo.isPass() ? "已审核" : "未审核") + "</a></td>");
				out.print("</tr>");
			}
		
		
		%>
	</table>
	
	<% 		
		}
	%>
</body>
</html>
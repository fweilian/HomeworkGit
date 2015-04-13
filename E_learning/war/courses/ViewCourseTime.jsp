<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fan.gae.learning.course.*"%>
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
		if (user == null) {
			response.sendRedirect("/");
		} else {
			String courseName = request.getParameter("courseName");
			ConcreteCourseFactory cf = new ConcreteCourseFactory();
			CourseInfo courseInfo = cf.getCourse(courseName, 1).get(0);
	%>
	<center>
		<h1><%=courseName%></h1>
	</center>
	<table width=80%>
		<tr>
			<td>课时</td>
			<td>课时名</td>
			<td>课时简介</td>
		</tr>
		<%
			int i = 0;
				for (String catalogue : courseInfo.getCatalogue()) {
					out.print("<tr>");
					out.print("<td>" + ++i + "</td>");
					out.print("<td><a href='/courses/ViewCourseVideo.jsp?courseName=" +courseInfo.getCourseName()+"&courseTime="+i+"'>"+ new String(courseInfo.getCourseTitle()[i-1].getBytes("iso-8859-1"),"utf-8") + "</a></td>");
					out.print("<td>" + new String(catalogue.getBytes("iso-8859-1"),"utf-8") + "</td>");
					out.print("</tr>");
				}
		%>
	</table>
	<a href="/login/teacherInfo.jsp">back</a>
	<%
		}
	%>
</body>
</html>
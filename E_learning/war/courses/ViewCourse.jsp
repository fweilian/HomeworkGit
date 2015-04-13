<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="fan.gae.learning.course.*"%>
<%@ page import="fan.gae.learning.user.*"%>
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
		ConcreteTeacherFactory tf = new ConcreteTeacherFactory();
		if (user == null) {
			response.sendRedirect("/");
		} else {
			TeacherInfo teacherInfo = tf.getTeacher(user.getUserId())
					.get(0);
	%>
	<center>
		<h1>我的课程信息</h1>
	</center>
	<table width=80%>
		<tr>
			<td>课程名</td>
			<td>课时</td>
			<td>课程类型</td>
			<td>课程简介</td>
			<td>审核</td>
			<td>喜欢</td>
			<td>不喜欢</td>
			<td>作业</td>
		</tr>
		<%
			ConcreteCourseFactory cf = new ConcreteCourseFactory();
				List<CourseInfo> courseInfos = cf.getCourse(
						teacherInfo.getAutoId(), 0);
				for (CourseInfo courseInfo : courseInfos) {
					out.print("<tr>");
					out.print("<td><a href='ViewCourseTime.jsp?courseName=" + courseInfo.getCourseName() + "'>"+courseInfo.getCourseName()+"</a></td>");
					out.print("<td>" + courseInfo.getCourseTime() + "</td>");
					out.print("<td>" + courseInfo.getCourseType() + "</td>");
					out.print("<td>" + courseInfo.getCourseIntroduce()
							+ "</td>");
				 	out.print("<td>" + (courseInfo.isPass() ? "通过":"未审核") + "</td>");
					out.print("<td>" + courseInfo.getLike() + "</td>");
					out.print("<td>" + courseInfo.getUnLike() + "</td>");
					out.print("<td><a href='/homework/Homework.jsp?courseName=" + courseInfo.getCourseName()+"'>作业</a></td>");
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
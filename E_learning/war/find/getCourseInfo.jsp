<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="fan.gae.learning.course.*"%>
<%@ page import="fan.gae.learning.user.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String courseName = request.getParameter("courseName");
		if (courseName == null) {
			response.sendRedirect("/find/findCourse.jsp");
		} else {
			ConcreteCourseFactory cf = new ConcreteCourseFactory();
			ConcreteTeacherFactory tf = new ConcreteTeacherFactory();

			CourseInfo courseInfo = cf.getCourse(courseName, 1).get(0);
			TeacherInfo teacherInfo = tf.getTeacher(
					courseInfo.getTeacherId()).get(0);
	%>
	
	<h3><%=courseName %></h3>
	<p><%=teacherInfo.getRealName() %>,<%=teacherInfo.getTeachField() %></p>
	<form action="/courses/ViewCourseVideo.jsp" method="get">
		<input type="hidden" name="courseName" value="<%=courseName %>">
		<input type="hidden" name="courseTime" value="1">
		<input type="submit" name="submit" value="开始学习">
	</form>
	
	<div>
		<br><br>
		<%
			System.out.println("dndkineir * "+courseInfo.getCourseTime().length());
			int i = 0;
			for(String courseTitle : courseInfo.getCourseTitle()) {
		%>
			<a href="/courses/ViewCourseVideo.jsp?courseName=<%=courseName %>&courseTime=<%=i+1 %>">
				课时<%=i+1 %>&nbsp;&nbsp;
				<% System.out.println("dndkineir ^^* "+courseTitle);%>
				<%=new String(courseTitle.getBytes("iso-8859-1"),"utf-8") %>
			</a>
			<br>
		<% 
				
			}
		%>
	</div>
	<%
		}
	%>

</body>
</html>
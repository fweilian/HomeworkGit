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
	<center>
		<h3>课程</h3>
	<div>
		<a href="findCourse.jsp?type_value=fullCourse">全部课程</a>     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="findCourse.jsp?type_value=itCourse">IT与互联网</a>     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="findCourse.jsp?type_value=foreignCourse">外语学习</a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href="findCourse.jsp?type_value=economicCourse">经管金融</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="findCourse.jsp?type_value=examCourse">考试认证</a>     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="findCourse.jsp?type_value=humanCourse">人文历史</a>    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="findCourse.jsp?type_value=lifeCourse">生活家居</a>
	</div>
	<br>
	<table width=80%>
		<tr>
			<td>课程名</td>
			<td>类型</td>
			<td>教学者</td>
			<td>简介</td>
			<td>学时</td>
			<td>喜欢</td>
		</tr>
		<%
			String type = request.getParameter("type_value");

			if (type == null) {
				type = "fullCourse";
			}
			ConcreteCourseFactory cf = new ConcreteCourseFactory();
			ConcreteTeacherFactory tf = new ConcreteTeacherFactory();
			List<CourseInfo> courseInfos = cf.getCourseFromType(type);

			for (CourseInfo courseInfo : courseInfos) {
				if (courseInfo.isPass()) {
					TeacherInfo teacherInfo = tf.getTeacher(
							courseInfo.getTeacherId()).get(0);
					out.print("<tr>");
					out.print("<td><a href='/find/getCourseInfo.jsp?courseName="
							+ courseInfo.getCourseName()
							+ "'>"
							+ courseInfo.getCourseName() + "</a></td>");
					out.print("<td>" + courseInfo.getCourseType() + "</td>");
					out.print("<td><a href='/find/getTeacherInfo.jsp?teacherId="
							+ teacherInfo.getAutoId()
							+ "'>"
							+ teacherInfo.getRealName() + "</a></td>");
					out.print("<td>" + courseInfo.getCourseIntroduce()
							+ "</td>");
					out.print("<td>"+courseInfo.getCourseTime() + "</td>");
					out.print("<td>"+ courseInfo.getLike() + "</td>");
					out.print("</tr>");
				}

			}
		%>
	</table>
	</center>
</body>
</html>
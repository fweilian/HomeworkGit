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
		String teacherId = request.getParameter("teacherId");
		if(teacherId == null) {
			response.sendRedirect("/find/findCourse.jsp");
		} else {
			ConcreteTeacherFactory tf = new ConcreteTeacherFactory();
			TeacherInfo teacherInfo = tf.getTeacher(teacherId).get(0);
			
	%>
	
		<div>
			<h3><%=teacherInfo.getRealName() %></h3>
			<p><%=teacherInfo.getTeachField() %></p>
		</div>
		
		<div>
			<a href="getTeacherInfo.jsp?teacherId=<%=teacherId %>&content=home">主页</a>
			<a href="getTeacherInfo.jsp?teacherId=<%=teacherId %>&content=course">课程</a>
			<a href="getTeacherInfo.jsp?teacherId=<%=teacherId %>&content=comment">评论</a>
			<a href="getTeacherInfo.jsp?teacherId=<%=teacherId %>&content=message">留言板</a>
		</div>
		
		<%
			String content = request.getParameter("content");
			if(content==null) {
				content = "home";
			} 
			ConcreteChooseCourseFactory ccf = new ConcreteChooseCourseFactory();
			List<ChooseCourseInfo> chooseCourseInfos = ccf.getChooseCourse(teacherInfo.getAutoId());
			ConcreteCourseFactory cf = new ConcreteCourseFactory();
			CourseInfo courseInfo = new CourseInfo();
			if(content.equals("home")) {
			String study = "学过";
			String studying = "正在学";
			out.print("<p>"+teacherInfo.getRealName()+"的学习轨迹</p>");
			for(ChooseCourseInfo chooseCourseInfo : chooseCourseInfos) {
				if(!chooseCourseInfo.getUserId().equals(teacherInfo.getAutoId())) {
					System.out.println("1234567");
					courseInfo = cf.getCourse(chooseCourseInfo.getCourseId(), 2).get(0);
					out.print("<p>" + chooseCourseInfo.getStudyTime().toLocaleString()+"</p>");
					out.print("<p>" + (chooseCourseInfo.isState() ? study : studying) +"&nbsp;&nbsp&nbsp;");
					out.print("&nbsp;&nbsp;&nbsp;<a href='/find/getCourseInfo.jsp?courseName="+ courseInfo.getCourseName() +"'>" + courseInfo.getCourseName()+"</a></p>");
					out.print("<br><br>");
				}
			}
			} else if(content.equals("course")){
				List<CourseInfo> courseInfos = cf.getCourse(teacherInfo.getAutoId(), 0);
				for(CourseInfo course : courseInfos) {
					out.print("<p><a href='/find/getCourseInfo.jsp?courseName="+ course.getCourseName() +"'>" + course.getCourseName() +"</a></p>");
					out.print("<p>" + course.getCourseIntroduce() + "</p>");
					out.print("<br><br>");
				}
			}
		%>
			
	<% 
		}
	%>

</body>
</html>
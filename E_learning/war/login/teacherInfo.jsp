<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
	<%@ page import="fan.gae.learning.course.*" %>
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
<jsp:include page="/login/login.jsp"></jsp:include>
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
	<input class="teacherId" type="hidden"
		value="<%=teacherInfo.getUserId()%>" />
	<div class="personal_info">
			<h1><%=teacherInfo.getUserName()%></h1>
			<p class="edit">
				<a class="setting" href="/login/editTeacherInfo.jsp" title="编辑资料">编辑个人资料</a>
			</p>
			
			<div class="upload_course">
				<a class="view_course" href="/courses/ViewCourse.jsp">查看课程</a>
				<a class="apply_course" href="/courses/ApplyCourse.jsp">申请课程</a>
				<a class="upload_course" href="/courses/UploadCourse.jsp">上传课时</a>
			</div>
	</div>
	<br><br>
	<div class="main_info">
		<a href="teacherInfo.jsp?content=home">主页</a> 
		<a href="teacherInfo.jsp?content=comment">评论</a> 
		<a href="teacherInfo.jsp?content=message">留言板</a>
	</div>

	<%
		String content = request.getParameter("content");
		if(content == null) {
			content = "home";
		}
		ConcreteChooseCourseFactory ccf = new ConcreteChooseCourseFactory();
		List<ChooseCourseInfo> chooseCourseInfos = ccf.getChooseCourse(teacherInfo.getAutoId());
		ConcreteCourseFactory cf = new ConcreteCourseFactory();
		CourseInfo courseInfo = new CourseInfo();
		if(content.equals("home")) {
			String study = "学过";
			String studying = "正在学";
			out.print("<h4>我的学习轨迹</h4>");
			for(ChooseCourseInfo chooseCourseInfo : chooseCourseInfos) {
				courseInfo = cf.getCourse(chooseCourseInfo.getCourseId(), 2).get(0);
				out.print("<p>" + chooseCourseInfo.getStudyTime().toLocaleString()+"</p>");
				out.print("<p>" + (chooseCourseInfo.isState() ? study : studying) +"&nbsp;&nbsp&nbsp;");
				out.print("&nbsp;&nbsp;&nbsp;<a href='/find/getCourseInfo.jsp?courseName="+ courseInfo.getCourseName() +"'>" + courseInfo.getCourseName()+"</a></p>");
				out.print("<br><br>");
			}
		} else if(content.equals("comment")) {
			
		}
		
	%>
	<% 
		}
	%>
</body>
</html>
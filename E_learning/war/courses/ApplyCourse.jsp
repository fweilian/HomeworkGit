<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<form action="/applyCourse" method="post">
		<p class="edit_text_style">填写申请课程信息</p>
		<br> 课程名<br> <input type="text" name="course_name" value="" />
		<br>课程类型<br> 
			<input type="checkbox" name="course_type" value="itCourse">IT与互联网
			<input type="checkbox" name="course_type" value="foreignCourse">外语学习
			<input type="checkbox" name="course_type" value="economicCourse">经管金融
			<input type="checkbox" name="course_type" value="examCourse">考试认证
			<input type="checkbox" name="course_type" value="humanCourse">人文历史
			<input type="checkbox" name="course_type" value="lifeCourse">生活家居
		<br>开课人<br> <input type="text" name="course_teacher" value="<%=teacherInfo.getRealName() %>" readonly/> 
		<br>课时<br> <input type="text" name="course_time" value="" />小时
		<br>课程简介<br>
		<textarea rows="5" cols="30" name="course_introduce"></textarea>
		<br> <input type="submit" name="submit" value="完成，上传课件" /> 
		<input type="reset" name="reset" value="取消" />
	</form>
	<%
		}
	%>
</body>
</html>
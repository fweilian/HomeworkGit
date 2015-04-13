<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fan.gae.learning.user.TeacherInfo"%>
<%@ page import="fan.gae.learning.user.ConcreteTeacherFactory"%>
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


	<div class="edit_item">
		<form action="/editTeacherInfo" method="post">
			<h1>个人基本资料设置</h1>
			用户名<br>
			<input type="text" id="username" name="username"
				value="<%=teacherInfo.getUserName()%>" /><br> 密码<br>
			<input type="password" id="password" name="password"
				value="<%=teacherInfo.getPassword()%>" /><br> 邮箱<br>
			<input type="email" id="email" name="email"
				value="<%=teacherInfo.getEmail()%>" /><br> 联系电话<br>
			<input type="text" id="phone_number" name="phone_number"
				value="<%=teacherInfo.getPhoneNumber()%>" /><br>
			<h1>个人教学设置</h1>
			教授领域<br>
			<input type="text" id="teach_field" name="teach_field"
				value="<%=teacherInfo.getTeachField()%>" /><br> 近期教授计划<br>
			<textarea rows="5" cols="20" name="teach_plan"><%=teacherInfo.getTeachPlan()%></textarea>
			<br> 教授经验<br>
			<textarea rows="5" cols="20" name="teach_experience"><%=teacherInfo.getTeachExperience()%></textarea>
			<br> <input type="submit" name="submit" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" name="reset" value="取消">
		</form>
	</div>
	<%
		}
	%>
</body>
</html>
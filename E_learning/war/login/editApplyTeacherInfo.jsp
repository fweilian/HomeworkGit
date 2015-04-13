<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="fan.gae.learning.user.UserInfo"%>
<%@ page import="fan.gae.learning.user.TeacherInfo"%>
<%@ page import="fan.gae.learning.user.ConcreteUserFactory"%>
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

	<%--
	//	UserService userService = UserServiceFactory.getUserService();
	//	User user = userService.getCurrentUser();
	//	ConcreteTeacherFactory tf = new ConcreteTeacherFactory();
	//	if(user==null) {
	//		response.sendRedirect("/");
	//	} else {
	//		TeacherInfo teacherInfo = tf.getTeacher(user.getUserId()).get(0);
		
	//	System.out.println("edit apply teacher info " +teacherInfo);
	--%>
	<%
		ConcreteTeacherFactory tf = new ConcreteTeacherFactory();
		List<TeacherInfo> teacherInfos = tf.TeacherInfo();
	%>
	<form action="/applyTeacherHandle" method="post">
		<p class="edit_text_style">填写申请信息</p>
		<br> 真实姓名<br> <input type="text" name="real_name" value="" /><br>
		一句话介绍<br> <input type="text" name="one_introduce" value="" /><br>
		联系电话<br> <input type="text" name="phone_number" value="" /><br>
		<br> 教授的领域<br> <input type="text" name="teach_field"
			value="" placeholder="请输入您希望教授的领域" autocomplete="off" /><br>
		近期授课计划<br>
		<textarea rows="5" cols="20" name="teach_plan"></textarea><br>
		授课经验<br>
		<textarea rows="5" cols="20" name="teach_experience"></textarea><br>
		<input type="submit" name="submit" value="完成，提交申请" /> <input
			type="reset" name="reset" value="取消" />
	</form>
	<%--} --%>
</body>
</html>
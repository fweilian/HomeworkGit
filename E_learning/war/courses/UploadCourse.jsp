<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="fan.gae.learning.user.*"%>
<%@ page import="fan.gae.learning.course.*"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
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
	<%
		BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	%>
	<form action="<%=blobstoreService.createUploadUrl("/uploadCourse") %>" method="post" enctype="multipart/form-data">
		<p class="edit_text_style">填写上传课时信息</p>
		课程视频：<br>
		<input type="file" name="myFile">
		<br> 课程名 <br>
		<%
			ConcreteCourseFactory cf = new ConcreteCourseFactory();
				List<CourseInfo> courseInfos = (List<CourseInfo>) cf.getCourse(
						user.getUserId(), 0);
				if (courseInfos.isEmpty()) {
					response.sendRedirect("/login/teacherInfo.jsp");
				} else {
					for (CourseInfo courseInfo : courseInfos) {
		%>
		<input type="checkbox" name="course_name"
			value="<%=courseInfo.getCourseName()%>"><%=courseInfo.getCourseName()%>
		<%
			}
				}
		%>
		课时选择 <select id="course_time" name="course_time"></select> <br>课时标题<br>
		<input type="text" name="course_title" value="" /><br> 课时简介<br>
		<textarea rows="5" cols="30" name="course_introduce"></textarea><br>
		<input type="submit" value="上传"> 
	</form>
	<%
		}
	%>
</body>

<script type="text/javascript" language="javascript">
	var select = document.getElementById("course_time");
	for (var i = 1; i <= 24; i++) {
		var opt = document.createElement("option");
		opt.value = i;
		opt.innerHTML = i;
		select.appendChild(opt);
	}
</script>

</html>
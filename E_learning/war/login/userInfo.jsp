<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="error.jsp" pageEncoding="UTF-8"%>
	<%@ page import="java.util.*" %>
	<%@ page import="fan.gae.learning.course.*" %>
<%@ page import="fan.gae.learning.user.UserInfo"%>
<%@ page import="fan.gae.learning.user.ConcreteUserFactory"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/home.css">
<title>Insert title here</title>
</head>

<body>
<jsp:include page="/login/login.jsp"></jsp:include>
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		ConcreteUserFactory uf = new ConcreteUserFactory();
		if (user == null) {
			response.sendRedirect("/");
		} else {
			List<UserInfo> userInfos = uf.getUser(user.getUserId());
			///for(int i=0;i<1;i++) {
				UserInfo userInfo = userInfos.get(0);
			
	%>

	<input id="userId" type="hidden" value="<%=userInfo.getUserId()%>" />
	<div class="personal_info">
		
		<div class="info_text">
			<h1>
				<%=userInfo.getUserName()%>
			</h1>
			<p class="edit">
				<a class="setting" href="/login/editUserInfo.jsp" title="编辑资料">编辑个人资料</a>
			</p>
		</div>
	</div>

	<div class="main_info">
		<a href="userInfo.jsp?content=home">主页</a> 
		<a href="userInfo.jsp?content=comment">评论</a> 
		<a href="userInfo.jsp?content=message">留言板</a>
	</div>

	<%
		String content = request.getParameter("content");
		if(content == null) {
			content = "home";
		}
		ConcreteChooseCourseFactory ccf = new ConcreteChooseCourseFactory();
		List<ChooseCourseInfo> chooseCourseInfos = ccf.getChooseCourse(userInfo.getAutoId());
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
				out.print("&nbsp;&nbsp;&nbsp;<a href='/find/getCourseInfo.jsp?courseName="+ courseInfo.getCourseName() +"'>" + courseInfo.getCourseName()+"</a>");
				out.print("&nbsp;&nbsp;&nbsp;<a href='/homework/Homework.jsp?courseName="+courseInfo.getCourseName()+"&study=1'>课程作业</a></p>");
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
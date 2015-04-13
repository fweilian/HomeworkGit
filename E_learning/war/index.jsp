<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fan.gae.learning.user.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/css/main.css">
<title>Insert title here</title>
</head>
<body>

	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "登录";
		System.out.println(url);
		List<UserInfo> userInfo = new ArrayList<UserInfo>();
		//System.out.println("wfeeeee"+user.getUserId());
		ConcreteUserFactory uf = new ConcreteUserFactory();
		if (user != null) {
			url = userService.createLogoutURL(request.getRequestURI());
			urlLinktext = "退出";
			System.out.println("user");
			userInfo = uf.getUser(user.getUserId());
			System.out.println("hfshedi" + userInfo);
			if (userInfo.isEmpty()) {
				System.out.println("us" + user.getUserId());
				System.out.println("us" + user.getNickname());
				System.out.println("us" + user.getEmail());
				System.out.println("us" + new Date());
				boolean state = uf.AddUser(user.getUserId(),
						user.getNickname(), user.getNickname(), 1,
						user.getEmail(), new Date(), 1);
			}
		}
	%>


	<div class="main_inner_wrap">

		<a class="e_learning_logo" href="/" title="E_learning">E_learning</a>&nbsp;&nbsp;&nbsp;
		<a class="main_home" href="/e_learning">首页</a> <a class="main_home"
			href="/find/findCourse.jsp">课程</a> <a class="main_home"
			href="/discuss/group.jsp">论坛</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" placeholder="search"> &nbsp;&nbsp;

		<%
			if (user == null) {

			} else {
		%>
		<a class="header_login"
			href="/login/JudgeUser.jsp?userId=<%=user.getUserId()%>"><%=user.getNickname()%></a>
		<%
			}
		%>
		<a class="header_login" rel="nofollow" href="<%=url%>"><%=urlLinktext%></a>
	</div>
	<br>
	<div class="main_left">
		<br><br>
			<p>热门课程。。。</p>
			<a href="/courses/new/3.jsp"> <img src="/images/courses/3.jpg"
				width="80" height="80" title="3" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="/courses/new/4.jsp">
				<img src="/images/courses/4.jpg" width="80" height="80" title="3" />
			</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/courses/new/5.jsp"> <img src="/images/courses/5.jpg"
				width="80" height="80" title="3" />
			</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/courses/new/6.jsp"> <img src="/images/courses/6.jpg"
				width="80" height="80" title="3" />
			</a>
			<p>
				<a href="/courses/new/3.jsp">GAE Programming</a> &nbsp;&nbsp;<a
					href="/courses/new/4.jsp">计算机网络</a>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="/courses/new/5.jsp">公开课</a>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="/courses/new/6.jsp">计算机科学</a>
			</p>
			<p>
				<a href="/teachers/teacher1,jsp">teacher</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="/teachers/teacher1,jsp">teacher</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<a
					href="/teachers/teacher1,jsp">teach1</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="/teachers/teacher1,jsp">teach1</a>
			</p>
			<a href="/courses/new/3.jsp"> <img src="/images/courses/3.jpg"
				width="80" height="80" title="3" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="/courses/new/4.jsp">
				<img src="/images/courses/4.jpg" width="80" height="80" title="3" />
			</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/courses/new/5.jsp"> <img src="/images/courses/5.jpg"
				width="80" height="80" title="3" />
			</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/courses/new/6.jsp"> <img src="/images/courses/6.jpg"
				width="80" height="80" title="3" />
			</a>
			<p>
				<a href="/courses/new/3.jsp">GAE Programming</a> &nbsp;&nbsp;<a
					href="/courses/new/4.jsp">计算机网络</a>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="/courses/new/5.jsp">公开课</a>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="/courses/new/6.jsp">计算机科学</a>
			</p>
			<p>
				<a href="/teachers/teacher1,jsp">teacher</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="/teachers/teacher1,jsp">teacher</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<a
					href="/teachers/teacher1,jsp">teach1</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="/teachers/teacher1,jsp">teach1</a>
			</p>
		

	</div>
	<div class="main_right">
	<br><br>
	<p>讨论区...</p>
	<a href="/discuss.jsp">GAE Programming课程讨论</a><br>
	<a href="/discuss.jsp">计算机网站课程讨论</a><br>
	<a href="/discuss.jsp">计算机科学课程讨论</a><br>
	<a href="discuss.jsp">外语学习攻略</a>
	<br><br>
	<p>申请成为老师...</p>
	<form action="/applyTeacher" method="post">
			<input type="submit" name="applyTeacher" value="申请成为老师" />
		</form>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>

<%@ page import="fan.gae.learning.user.*"%>
<%@ page import="fan.gae.learning.course.*"%>
<%@ page import="fan.gae.learning.video.*"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%@ page import="com.google.appengine.api.blobstore.BlobInfo"%>
<%@ page import="com.google.appengine.api.blobstore.BlobInfoFactory"%>

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
		String courseName = request.getParameter("courseName");
		String courseTime = request.getParameter("courseTime");
		if (user == null) {
			response.sendRedirect(userService
					.createLoginURL("/find/getCourseInfo.jsp"));
		} else {

			ConcreteCourseFactory cf = new ConcreteCourseFactory();
			System.out.println("134242342**" + courseName + " time * "
					+ courseTime);
			CourseInfo courseInfo = cf.getCourse(courseName, 1).get(0);
			System.out
					.println("134242342****" + courseInfo.getCourseName());
			ConcreteVideoFactory vf = new ConcreteVideoFactory();
			List<VideoInfo> videoInfos = (List<VideoInfo>) vf
					.getVideo(courseInfo.getTeacherId());
			for (VideoInfo v : videoInfos) {
				System.out.println("for video **** " + v.getCourseName());
			}
	%>

	<center>
		<h1><%=courseName%></h1>
		<h3>
			第<%=courseTime%>课
		</h3>
	</center>

	<%
		BlobstoreService blobstoreService = BlobstoreServiceFactory
					.getBlobstoreService();
	%>

	<%
		Iterator<BlobInfo> iterator = null;
			for (VideoInfo videoInfo : videoInfos) {
				System.out.println("swwws00" + videoInfo.getBlobKey());
				iterator = new BlobInfoFactory().queryBlobInfos();
				while (iterator.hasNext()) {
					BlobInfo blobInfo = iterator.next();

					if (blobInfo
							.getBlobKey()
							.toString()
							.equals("<BlobKey: "
									+ videoInfo.getBlobKey().getKeyString()
									+ ">")
							&& videoInfo.getCourseName().equals(courseName)
							&& videoInfo.getCourseTime().equals(
									String.valueOf(Integer
											.parseInt(courseTime) - 1))) {
						System.out.println("swwws");
	%>
	<center>
		<video controls autoplay name="media"> 
			<source src="/serve?blob-key=<%=videoInfo.getBlobKey().getKeyString()%>" type="video/mp4">
		</video>
	</center>
	<!-- 
	<embed src="/_ah/admin/<%=videoInfo.getBlobKey().getKeyString()%>" height="640"
		width="450" autostart="true">
	</embed>
	 -->
	<%
		ConcreteChooseCourseFactory ccf = new ConcreteChooseCourseFactory();
						if (user.getUserId() != courseInfo.getTeacherId()) {
							boolean flag = ccf.AddChooseCourse(user
									.getUserId(), String.valueOf(courseInfo
									.getCourseId()), new Date(), false);
							if (!flag) {
								response.sendRedirect("/find/findCourse.jsp");
							}
						}
					}
				}
			}
	%>

	<%
		}
	%>

</body>
</html>
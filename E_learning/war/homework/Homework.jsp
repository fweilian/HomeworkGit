<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>

<%@ page import="fan.gae.learning.user.*"%>
<%@ page import="fan.gae.learning.course.*"%>
<%@ page import="fan.gae.learning.homework.*"%>
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
		if (user == null) {
			response.sendRedirect("/");
		} else {
			String courseName = request.getParameter("courseName");
			String study = request.getParameter("study");
			if(study==null) {study ="2"; }
			ConcreteCourseFactory cf = new ConcreteCourseFactory();
			CourseInfo courseInfo = cf.getCourse(courseName, 1).get(0);
			ConcreteHomeworkFactory hf = new ConcreteHomeworkFactory();
			//HomeworkInfo homeworkInfo = new HomeworkInfo();
			List<HomeworkInfo> homeworks = (List<HomeworkInfo>) hf
					.getHomework(courseInfo.getTeacherId(), courseName);
			System.out.println("courseName " + courseName);
	%>
	<center>
		<h1><%=courseName%></h1>
	</center>
	<table width=80%>
		<tr>
			<td>作业名</td>
			<td>课程名</td>
			<td>完成方式</td>
			<td>大小</td>
			<td>上传时间</td>
			<td>下载次数</td>
			<td>下载</td>
		</tr>
		<%
			BlobstoreService blobstoreService = BlobstoreServiceFactory
						.getBlobstoreService();
		%>
		<%
			Iterator<BlobInfo> iterator = null;
				//BlobInfoFactory bf = new BlobInfoFactory();

				for (HomeworkInfo homeworkInfo : homeworks) {
					iterator = new BlobInfoFactory().queryBlobInfos();
					while (iterator.hasNext()) {
						BlobInfo blobInfo = iterator.next();

						if (blobInfo
								.getBlobKey()
								.toString()
								.equals("<BlobKey: "
										+ homeworkInfo.getBlobKey()
												.getKeyString() + ">")&&homeworkInfo.getCourseName().equals(courseName)) {
							out.print("<tr>");
							out.print("<td>"
									+ new String(blobInfo.getFilename()
											.getBytes("iso-8859-1"), "utf-8")
									+ "</td>");
							out.print("<td>" + homeworkInfo.getCourseName()
									+ "</td>");
							out.print("<td>" + new String(homeworkInfo.getFinishStyle().getBytes("iso-8859-1"),"utf-8")
									+ "</td>");
							out.print("<td>" + blobInfo.getSize() / 1024
									+ "kb</td>");
							out.print("<td>"
									+ homeworkInfo.getUploadTime()
											.toLocaleString() + "</td>");
							out.print("<td>" + homeworkInfo.getDownloadTimes()
									+ "</td>");
							out.print("<td><a href='/homework/Download.jsp?blob-key="
									+ homeworkInfo.getBlobKey().getKeyString()
									+ "'>下载</a></td>");
							out.print("</tr>");
						}
					}
				}
		%>
	</table>
	<% if(!study.equals("1")) { %>
	<form action="<%=blobstoreService.createUploadUrl("/uploadFile")%>"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="course_name"
			value="<%=courseInfo.getCourseName()%>"> <input type="file"
			name="myFile"> 完成方式：<input type="text" name="finish_style"
			value=""><input type="submit" value="Submit">
	</form>
	<%}
		}
	%>
</body>
</html>
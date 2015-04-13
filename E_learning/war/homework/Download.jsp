<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="com.google.appengine.api.blobstore.BlobInfo"%>
<%@ page import="com.google.appengine.api.blobstore.BlobInfoFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobKey"%>
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
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		BlobKey blobKey = new BlobKey(request.getParameter("blob-key"));
		BlobInfoFactory bf = new BlobInfoFactory();
		BlobInfo blobInfo = bf.loadBlobInfo(blobKey);
		response.setContentType("application/x-download");
		String filedownload = "/WEB-INF/appengine-generated/"
				+ request.getParameter("blob-key");
		String fileDisplay = blobInfo.getFilename();// String(blobInfo.getFilename().getBytes("iso-8859-1"), "UTF-8");
		fileDisplay = URLEncoder.encode(fileDisplay, "utf-8");
		response.setContentType(blobInfo.getContentType());
		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileDisplay);

		try {
			RequestDispatcher dis = application
					.getRequestDispatcher(filedownload);
			if (dis != null) {
				dis.forward(request, response);
			}
			response.flushBuffer();
			out.clear();
			out = pageContext.pushBody();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
	%>
</body>
</html>
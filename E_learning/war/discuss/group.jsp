<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="fan.gae.learning.discuss.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
.group_left {
	float: left;
	width: 70%;
}

.group_right {
	float: right;
	width: 30%;
}
</style>
<body>
	<div>
		<a href="group.jsp?type_value=topic">话题</a> <a
			href="group.jsp?type_value=group">小组</a>
	</div>

	<div class="group_left">
		<% 
			String type = request.getParameter("type_value");

			if (type == null || type=="topic") {
				ConcreteTopicFactory tf = new ConcreteTopicFactory();
				List<TopicInfo> topicInfos = tf.getTopic(true);
				if(!topicInfos.isEmpty()) {
				for (TopicInfo topicInfo : topicInfos) {
					out.print("<br>");
					out.print("<a href='/discuss/topic.jsp?topic_name="+topicInfo.getTopicTitle()+"&group_name="+topicInfo.getGroupName()+"'>"+topicInfo.getTopicTitle()+"</a>");
					out.print(topicInfo.getReplyNumber());
					out.print(topicInfo.getTopicLeaseTime());
					out.print("<a href='/discuss/groupInfo.jsp?group_name="+topicInfo.getGroupName()+"'>"+topicInfo.getGroupName()+"</a>");
					out.print("<br>");
				}
				}
			} else {
				ConcreteBBSGroupFactory bgf = new ConcreteBBSGroupFactory();
				List<BBSGroupInfo> bbsGroupInfos = bgf.getBBSGroup();
				if(!bbsGroupInfos.isEmpty()) {
				for(BBSGroupInfo bbsGroupInfo : bbsGroupInfos) {
					out.print("<br>");
					out.print("&nbsp;&nbsp;&nbsp;<a href='/discuss/groupInfo.jsp?group_name="+bbsGroupInfo.getGroupName()+"'>"+bbsGroupInfo.getGroupName()+"</a>&nbsp;&nbsp;&nbsp;");
					out.print("&nbsp;&nbsp;&nbsp;"+bbsGroupInfo.getGroupNumber()+"个成员&nbsp;&nbsp;&nbsp;");
					out.print("&nbsp;&nbsp;&nbsp;"+bbsGroupInfo.getGroupIntroduce()+"&nbsp;&nbsp;&nbsp;");
					//out.print("&nbsp;&nbsp;&nbsp;<a href='/discuss/joinGroup.jsp?group_name="+bbsGroupInfo.getGroupName()+"'>加入小组</a>");
					out.print("<br>");
				}
				}
			}
		%>
	</div>
	<div class="group_right">
		<form action="createBBSGroup.jsp" method="post">
			创建我的小组 <br> <input type="submit" name="submit" value="申请创建小组">
		</form>
	</div>
</body>
</html>
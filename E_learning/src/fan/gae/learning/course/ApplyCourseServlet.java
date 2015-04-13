package fan.gae.learning.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ApplyCourseServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("upload new course ");

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user != null) {
			
			String teacherId = user.getUserId();
			
			String courseName = req.getParameter("course_name");
			String courseType = req.getParameter("course_type");
			//String courseTeacher = req.getParameter("course_teacher");
			String courseTime = req.getParameter("course_time");
			String courseIntroduce = req.getParameter("course_introduce");
			
			ConcreteCourseFactory cf = new ConcreteCourseFactory();
			boolean state = cf.AddCourse(courseName, courseTime, null, teacherId, courseType, courseIntroduce, 0, 0);
			if(state) {
				resp.sendRedirect("/login/teacherInfo.jsp");
			} else {
				resp.sendRedirect("/");
			}
		} else {
			resp.sendRedirect("/");
		}

	}

}

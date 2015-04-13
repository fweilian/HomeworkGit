package fan.gae.learning.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class EditTeacherInfoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("edit teacher info ");

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		String phoneNumber = req.getParameter("phone_number");

		String teachField = req.getParameter("teach_field");
		String teachPlan = req.getParameter("teach_plan");
		String teachExperience = req.getParameter("teach_experience");

		ConcreteTeacherFactory tf = new ConcreteTeacherFactory();
		TeacherInfo teacherInfo = tf.getTeacher(user.getUserId()).get(0);

		boolean state = tf.ChangeTeacherInfo(teacherInfo, username, password,
				email, phoneNumber, teachField, teachPlan, teachExperience);
		System.out.println("change " );
		if(state) {
			resp.sendRedirect("/login/teacherInfo.jsp");
		} else {
			resp.sendRedirect("/");
		}

	}

}

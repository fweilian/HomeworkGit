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
public class ApplyTeacherHandleServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
System.out.println("12345678");
		if (user != null) {
			ConcreteUserFactory uf = new ConcreteUserFactory();
			UserInfo userInfo = uf.getUser(user.getUserId()).get(0);
			if (userInfo != null) {
				String realName = req.getParameter("real_name");
				String oneIntroduce = req.getParameter("one_introduce");
				String phoneNumber = req.getParameter("phone_number");

				String teachField = req.getParameter("teach_field");
				String teachPlan = req.getParameter("teach_plan");
				String teachExperience = req.getParameter("teach_experience");

				ConcreteTeacherFactory tf = new ConcreteTeacherFactory();
				boolean state = tf.AddTeacher(userInfo, realName, oneIntroduce,
						phoneNumber, teachField, teachPlan, teachExperience);
				if (state) {
					System.out.println("create teacher success");
					resp.sendRedirect("/login/teacherInfo.jsp");
				} else {
					System.out.println("create teacher not success");
				}
			} else {
				resp.sendRedirect("/login/editApplyTeacherInfo.jsp");
			}
		} else {
			resp.sendRedirect("/");
		}
	}

}

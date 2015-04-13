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
public class EditUserInfoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("edit user info");
		
		//User user= (User)req.getAttribute("user");
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("password"+password);
		String email = req.getParameter("email");
		
		ConcreteUserFactory uf = new ConcreteUserFactory();
		UserInfo userInfo = uf.getUser(user.getUserId()).get(0);
		System.out.println("10009" + userInfo);
		boolean state = uf.ChangeUser(userInfo,username,password,email);
		System.out.println("change "+username+" "+ password + " " + email);
		if(state) {
			resp.sendRedirect("/login/userInfo.jsp");
		} else {
			resp.sendRedirect("/");             ///*********wrong*************/////
		}
		
	}

	
}

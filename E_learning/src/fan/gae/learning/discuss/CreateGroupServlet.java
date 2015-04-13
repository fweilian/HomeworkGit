package fan.gae.learning.discuss;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class CreateGroupServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if(user == null) {
			resp.sendRedirect("/discuss/group.jsp");
		} else {
			String groupName = req.getParameter("group_name");
			//String groupType = req.getParameter("group_type");
			String groupIntroduce = req.getParameter("group_introduce");
			
			ConcreteBBSGroupFactory bgf =  new ConcreteBBSGroupFactory();
			boolean state = bgf.AddGroup(user.getUserId(), groupName, groupIntroduce, 0, new Date(), new Date());
			if(state) {
				resp.sendRedirect("/discuss/group.jsp");
			} 
		}
	}

	
}

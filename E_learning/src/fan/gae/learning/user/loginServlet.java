package fan.gae.learning.user;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fan.gae.learning.google.EMFService;

@SuppressWarnings("serial")
public class loginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select u from UserInfo u where u.UserName = :UserName");
		q.setParameter("UserName", req.getParameter("username"));
		
		UserInfo userInfo = null;
		try {
			userInfo = (UserInfo) q.getSingleResult();
			
			if (userInfo != null) {
				resp.sendRedirect("/");
			} else {
				resp.sendRedirect("/login/login.jsp");
			}
		} finally {
			em.close();
		}
	}
}

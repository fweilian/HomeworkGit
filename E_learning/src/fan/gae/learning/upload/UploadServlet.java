package fan.gae.learning.upload;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fan.gae.learning.homework.ConcreteHomeworkFactory;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {

	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null) {
			resp.sendRedirect("/");
		} else {
			@SuppressWarnings("deprecation")
			Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
			BlobKey blobKey = blobs.get("myFile");
			if (blobKey == null) {
				resp.sendRedirect("/courses/ViewCourse.jsp");
			} else {
				String courseName = req.getParameter("course_name");
				System.out.println("courseName "+ courseName);
				String finishStyle = req.getParameter("finish_style");

				ConcreteHomeworkFactory hf = new ConcreteHomeworkFactory();
				boolean state = hf.AddHomework(blobKey, user.getUserId(),
						courseName, finishStyle, new Date(), 0);
				if (state) {
					resp.sendRedirect("/serve?blob-key="
							+ blobKey.getKeyString());
				} else {
					resp.sendRedirect("/courses/ViewCourse.jsp");
				}

			}
		}
	}

}

package fan.gae.learning.course;

import java.io.IOException;
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

import fan.gae.learning.video.ConcreteVideoFactory;

@SuppressWarnings("serial")
public class UploadCourseServlet extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("upload new course ");

		boolean state1 = false;
		boolean state2 = false;

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		ConcreteCourseFactory cf = new ConcreteCourseFactory();
		if (user == null) {
			resp.sendRedirect("/");
		} else {
			String courseName[] = req.getParameterValues("course_name");

			String courseTime = req.getParameter("course_time");
			String courseTitle = req.getParameter("course_title");
			String courseIntroduce = req.getParameter("course_introduce");

			@SuppressWarnings("deprecation")
			Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
			BlobKey blobKey = blobs.get("myFile");

			if (courseName == null) {
				resp.sendRedirect("/");
			} else {
				for (int i = 0; i < 1; i++) {
					System.out.println("1111" + courseName[i]);
					System.out.println("upload new course1 ");

					CourseInfo courseInfo = cf.getCourse(new String(courseName[i].getBytes("iso-8859-1"),"utf-8"), 1).get(
							0);
					state1 = cf.AddCourseCatalogue(courseInfo, courseTitle,
							courseIntroduce, courseTime);

					ConcreteVideoFactory vf = new ConcreteVideoFactory();
					state2 = vf.AddVideo(blobKey, user.getUserId(),
							new String(courseName[i].getBytes("iso-8859-1"),"utf-8"), courseTime, 0);

					if (state1 && state2) {
						resp.sendRedirect("/serve?blob-key="
								+ blobKey.getKeyString());
					} else {
						resp.sendRedirect("/");
					}
				}
			}
		}
	}

}

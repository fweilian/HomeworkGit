package fan.gae.learning.course;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fan.gae.learning.factory.CourseFactory;
import fan.gae.learning.google.EMFService;

public class ConcreteCourseFactory extends CourseFactory {

	@Override
	public boolean AddCourse(String courseName, String courseTime,
			String[] catalogue, String teacherId, String courseType,
			String courseIntroduce, int like, int unLike) {
		boolean state = false;
		System.out.println("create new course ");

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		CourseInfo courseInfo = new CourseInfo();
		txn.begin();
		try {
			courseInfo.setCourseName(courseName);
			courseInfo.setCourseTime(courseTime);
			courseInfo.setCatalogue(catalogue);

			courseInfo.setTeacherId(teacherId);
			courseInfo.setCourseType(courseType);
			courseInfo.setCourseIntroduce(courseIntroduce);

			courseInfo.setLike(like);
			courseInfo.setUnLike(unLike);

			em.persist(courseInfo);
			state = true;
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			state = false;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			em.close();
		}
		return state;
	}

	@Override
	public boolean ChangeCoursePass(CourseInfo courseInfo) {
		boolean state=false;
		System.out.println("change course pass ");
		
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			courseInfo = em.find(CourseInfo.class, courseInfo.getCourseId());
			em.clear();
			courseInfo.setPass(true);
			em.merge(courseInfo);
			txn.commit();
			state = true;
		} catch(Exception e) {
			e.printStackTrace();
			state = false;
		} finally {
			if(txn.isActive()) {
				txn.rollback();
			}
			em.close();
		}
		return state;
	}

	@Override
	public boolean AddCourseCatalogue(CourseInfo courseInfo,
			String courseTitle, String courseIntroduce, String courseTime) {
		System.out.println("add course time");
		boolean state = false;
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			int n = Integer.parseInt(courseTime);
			System.out.println("n=" + n);
			String[] catalogue = new String[n];
			String[] courseTimeTitle = new String[n];
			if (n == 1) {
				// System.out.println("courseInfo.getCatalogue " +
				// courseInfo.getCatalogue().length);
				courseTimeTitle[n - 1] = courseTitle;
				catalogue[n - 1] = courseIntroduce;
			} else {
				for (int i = 0; i < courseInfo.getCatalogue().length; i++) {
					courseTimeTitle[i] = courseInfo.getCourseTitle()[i];
					catalogue[i] = courseInfo.getCatalogue()[i];
				}
				courseTimeTitle[courseInfo.getCatalogue().length] = courseTitle;
				catalogue[courseInfo.getCatalogue().length] = courseIntroduce;
			}
			courseInfo = em.find(CourseInfo.class, courseInfo.getCourseId());
			em.clear();
			courseInfo.setCatalogue(catalogue);
			courseInfo.setCourseTitle(courseTimeTitle);
			em.merge(courseInfo);
			txn.commit();
			state = true;
		} catch (Exception e) {
			e.printStackTrace();
			state = false;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			em.close();
		}
		return state;
	}

	@Override
	public List<CourseInfo> getCourse(String courseValue, int flag) {
		System.out.println("get course info* " +courseValue);
		//System.out.println("get course info** " + Long.parseLong(courseValue));
		EntityManager em = EMFService.get().createEntityManager();
		String query = null;
		if (flag == 0) {
			query = "select c from CourseInfo c where c.TeacherId = :courseValue";
		} else if (flag == 1) {
			query = "select c from CourseInfo c where c.CourseName = :courseValue";
		} else {
			System.out.println("12345678");
			query = "select c from CourseInfo c where c.CourseId = :courseValue";
		}

		Query q = em.createQuery(query);
		if(flag!=2) {
		q.setParameter("courseValue", courseValue);
		} else {
			System.out.println("123456789");
			q.setParameter("courseValue", Long.parseLong(courseValue));
		}

		@SuppressWarnings("unchecked")
		List<CourseInfo> courseInfos = (List<CourseInfo>) q.getResultList();
		return courseInfos;
	}

	@Override
	public List<CourseInfo> getCourseFromType(String courseType) {
		System.out.println("get course info from type " +courseType);

		EntityManager em = EMFService.get().createEntityManager();
		String query = null;
		if (courseType.equals("fullCourse")) {
			System.out.println("dfghj");
			query = "select c from CourseInfo c";
		} else {
			System.out.println("dfhhjkghj");
			query = "select c from CourseInfo c where c.CourseType = :courseType";
		}

		Query q = em.createQuery(query);
		q.setParameter("courseType", courseType);
		@SuppressWarnings("unchecked")
		List<CourseInfo> courseInfos = (List<CourseInfo>) q.getResultList();
		return courseInfos;
	}

	@Override
	public boolean DeleteCourse(String courseName) {
		boolean state = false;
		System.out.println("delete a course ");

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			Query q = em
					.createQuery("delete from CourseInfo c where c.CourseName = :CourseName");
			q.setParameter("CourseName", courseName);
			q.executeUpdate();
			state = true;
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			state = false;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			em.close();
		}
		return state;
	}

	@Override
	public List<CourseInfo> getCourseFromPass(String Pass) {
		System.out.println("get course info from type " + Pass);

		EntityManager em = EMFService.get().createEntityManager();
		String query = null;
		if (Pass.equals("pass")) {
			query = "select c from CourseInfo c where c.Pass = true";
		} else {
			System.out.println("dfhhjkghj");
			query = "select c from CourseInfo c where c.Pass = false";
		}

		Query q = em.createQuery(query);
		
		@SuppressWarnings("unchecked")
		List<CourseInfo> courseInfos = (List<CourseInfo>) q.getResultList();
		return courseInfos;
	}

}

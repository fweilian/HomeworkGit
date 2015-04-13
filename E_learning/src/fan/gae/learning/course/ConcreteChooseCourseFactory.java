package fan.gae.learning.course;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fan.gae.learning.factory.ChooseCourseFactory;
import fan.gae.learning.google.EMFService;

public class ConcreteChooseCourseFactory extends ChooseCourseFactory {

	@Override
	public boolean AddChooseCourse(String userId, String courseId,
			Date studyTime, boolean state) {

		System.out.println("add choose course " + userId);
		boolean flag;

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		ChooseCourseInfo chooseCourseInfo = new ChooseCourseInfo();
		txn.begin();
		try {
			chooseCourseInfo.setUserId(userId);
			chooseCourseInfo.setCourseId(courseId);
			chooseCourseInfo.setStudyTime(studyTime);
			chooseCourseInfo.setState(state);

			em.persist(chooseCourseInfo);
			flag = true;
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			em.close();
		}
		return flag;
	}

	@Override
	public boolean ChangeStudyTime(ChooseCourseInfo chooseCourseInfo,
			Date studyTime) {

		System.out.println("change study time " + studyTime);
		boolean flag = false;

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			chooseCourseInfo = em.find(ChooseCourseInfo.class,
					chooseCourseInfo.getChooseId());
			em.clear();
			chooseCourseInfo.setStudyTime(studyTime);
			em.merge(chooseCourseInfo);
			txn.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			em.close();
		}
		return flag;
	}

	@Override
	public boolean ChangeStudyState(ChooseCourseInfo chooseCourseInfo,
			boolean state) {

		System.out.println("change stury state ");
		boolean flag = false;

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			chooseCourseInfo = em.find(ChooseCourseInfo.class,
					chooseCourseInfo.getChooseId());
			em.clear();
			chooseCourseInfo.setState(state);
			em.merge(chooseCourseInfo);
			txn.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			em.close();
		}
		return flag;
	}

	@Override
	public List<ChooseCourseInfo> getChooseCourse(String userId) {

		System.out.println("get choose course ");

		EntityManager em = EMFService.get().createEntityManager();

		Query q = em
				.createQuery("select c from ChooseCourseInfo c where c.UserId = :userId");
		q.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<ChooseCourseInfo> chooseCourseInfos = (List<ChooseCourseInfo>) q
				.getResultList();
		return chooseCourseInfos;
	}

}

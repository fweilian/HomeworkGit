package fan.gae.learning.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fan.gae.learning.factory.TeacherFactory;
import fan.gae.learning.google.EMFService;

public class ConcreteTeacherFactory extends TeacherFactory {

	@Override
	public boolean AddTeacher(UserInfo user, String realName,
			String oneIntroduce, String phoneNumber, String teachField,
			String teachPlan, String teachExperience) {

		boolean state = false;
		System.out.println("crete a teacher ");

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();

		txn.begin();
		TeacherInfo teacherInfo = new TeacherInfo();
		ConcreteUserFactory uf = new ConcreteUserFactory();
		try {
			uf.changeRole(user, 2);

			teacherInfo.setAutoId(user.getAutoId());
			teacherInfo.setUserName(user.getUserName());
			teacherInfo.setPassword(user.getPassword());
			teacherInfo.setRole(2);
			teacherInfo.setEmail(user.getEmail());
			teacherInfo.setRegisterTime(user.getRegisterTime());
			teacherInfo.setVisitTimes(user.getVisitTime());

			teacherInfo.setRealName(realName);
			teacherInfo.setOneIntroduce(oneIntroduce);
			teacherInfo.setPhoneNumber(phoneNumber);
			teacherInfo.setTeachField(teachField);
			teacherInfo.setTeachPlan(teachPlan);
			teacherInfo.setTeachExperience(teachExperience);

			em.persist(teacherInfo);
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
	public List<TeacherInfo> getTeacher(String userId) {

		EntityManager em = EMFService.get().createEntityManager();

		Query q = em
				.createQuery("select t from TeacherInfo t where t.AutoId = :UserId");
		q.setParameter("UserId", userId);
		System.out.println("userId " + userId);
		// TeacherInfo teacherInfo =
		// em.find(TeacherInfo.class,teacherInfo.getAutoId()getUserId());
		@SuppressWarnings("unchecked")
		List<TeacherInfo> teachers = (List<TeacherInfo>) q.getResultList();
		System.out.println("getTeacher()" + teachers);
		return teachers;
	}

	@Override
	public List<TeacherInfo> TeacherInfo() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from TeacherInfo t");
		@SuppressWarnings("unchecked")
		List<TeacherInfo> teacherInfos = q.getResultList();
		for (TeacherInfo teacherInfo : teacherInfos) {
			System.out.println("teacher " + teacherInfo.getRealName());
			System.out.println("teacher " + teacherInfo.getAutoId());
		}
		return teacherInfos;
	}

	@Override
	public boolean ChangeTeacherInfo(TeacherInfo teacherInfo, String userName,
			String password, String email, String phoneNumber,
			String teachField, String teachPlan, String teachExperience) {
		boolean state = false;
		System.out.println("change teacher info ");

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			teacherInfo = em.find(TeacherInfo.class, teacherInfo.getUserId());
			em.clear();

			teacherInfo.setUserName(userName);
			teacherInfo.setPassword(password);
			teacherInfo.setEmail(email);
			teacherInfo.setPhoneNumber(phoneNumber);

			teacherInfo.setTeachField(teachField);
			teacherInfo.setTeachPlan(teachPlan);
			teacherInfo.setTeachExperience(teachExperience);

			em.merge(teacherInfo);
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
}

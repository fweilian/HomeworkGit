package fan.gae.learning.user;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.Transient;


import fan.gae.learning.factory.UserFactory;
//import fan.gae.learning.factory.Factory;
import fan.gae.learning.google.EMFService;

//import fan.gae.learning.user;

public class ConcreteUserFactory extends UserFactory {
	@Transient
	private static Logger logger = Logger.getLogger(ConcreteUserFactory.class
			.getName());

	@Override
	public boolean AddUser(String autoId, String username, String password,
			int role, String email, Date date, int visittimes) {
		boolean state = false;
		System.out.println("crete new user");
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		UserInfo userInfo = new UserInfo(autoId, username, password, role,
				email, date, visittimes);
		try {
			em.persist(userInfo);
			System.out.println("crete new user2");
			txn.commit();
			logger.info("888888**************");
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
	public boolean ChangeUser(UserInfo user, String username, String password,
			String email) {
		boolean state = false;
System.out.println("userInfo "+user);
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			user = em.find(UserInfo.class,user.getUserId());
			
			em.clear();
			user.setUserName(username);
			user.setPassword(password);
			em.merge(user);
			//String id = user.getAutoId();
			/*Query q = em
					.createQuery("update UserInfo as u set u.UserName = :username and u.Password = :password where u.Email = :email");
			q.setParameter("UserName", username);
			q.setParameter("Pawword", password);
			q.setParameter("Email", email);
			//q.setParameter("AutoId", id);
			q.executeUpdate();*/
			txn.commit();
			state = true;
			// Query q = em.createQuery("update User as u set u. ")
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
	public boolean changeRole(UserInfo user,int role) {
		boolean state = false;

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			user = em.find(UserInfo.class, user.getUserId());
			em.clear();
			user.setRole(role);
			em.merge(user);
			//Query q = em
			//		.createQuery("update UserInfo as u set u.Password = :Password where u.UserName = :UserName");
			//q.setParameter("Password", user.getPassword());
			//q.setParameter("UserName", user.getUserName());
			//q.executeUpdate();
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
	public List<UserInfo> getUser(String UserId) { // query user info
		// List<User> users = null;
		EntityManager em = EMFService.get().createEntityManager();

		Query q = em
				.createQuery("select u from UserInfo u where u.AutoId = :UserId");
		q.setParameter("UserId", UserId);
		@SuppressWarnings("unchecked")
		List<UserInfo> users = (List<UserInfo>) q.getResultList();
		System.out.println("getUser()" + users);
		return users;

	}

	@Override
	public boolean DeleteUser(String UserName) {
		boolean state = false;

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			Query q = em
					.createQuery("delete from UserInfo u where u.UserName = :UserName");
			q.setParameter("UserName", UserName);
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
	public List<UserInfo> UserInfo() { // return all user info

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from UserInfo u");
		@SuppressWarnings("unchecked")
		List<UserInfo> userInfos = q.getResultList();
		return userInfos;
	}

	@Override
	public boolean isCheck(String UserName) {
		// UserInfo user = Factory.getInstance().getUser();
		// UserInfo user = new UserInfo();
		EntityManager em = EMFService.get().createEntityManager();
		// EntityTransaction txn = em.getTransaction();
		// txn.begin();
		Query q = em
				.createQuery("select u from UserInfo u where u.UserName = :UserName");
		q.setParameter("UserName", UserName);

		UserInfo userInfo = null;
		try {
			userInfo = (UserInfo) q.getSingleResult();
		} finally {
			em.close();
		}

		// @SuppressWarnings("unchecked")
		/*
		 * List<UserInfo> result = (List<UserInfo>)q.getResultList();
		 * System.out.println("dsgfsddsg"+result); DateFormat format = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); if(result != null) {
		 * 
		 * @SuppressWarnings("rawtypes") Iterator iterator = result.iterator();
		 * while(iterator.hasNext()) {System.out.println("0000000"+user);
		 * Object[] row = (Object[]) iterator.next();
		 * System.out.println(row[0].toString());
		 * System.out.println("(((((((((((((("+row[0].toString());
		 * user.setUserId((Long.parseLong(row[0].toString())));
		 * user.setUserName(row[1].toString());
		 * 
		 * user.setPassword(row[2].toString());
		 * user.setRole(Integer.parseInt(row[3].toString()));
		 * user.setEmail(row[4].toString());
		 * 
		 * try { user.setRegisterTime(format.parse(row[5].toString())); } catch
		 * (ParseException e) { e.printStackTrace(); }
		 * 
		 * user.setVisitTimes(Integer.parseInt(row[6].toString())); } }
		 */
		// em.close();

		return userInfo == null;
	}
}

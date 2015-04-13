package fan.gae.learning.discuss;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fan.gae.learning.factory.BBSGroupInfoFactory;
import fan.gae.learning.google.EMFService;

public class ConcreteBBSGroupFactory extends BBSGroupInfoFactory {

	@Override
	public boolean AddGroup(String UserId, String GroupName,
			String GroupIntroduce, int GroupNumber, Date CreateTime,
			Date ChangeTime) {
		System.out.println("add group ");
		boolean state = false;

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		BBSGroupInfo bbsGroupInfo = new BBSGroupInfo();
		txn.begin();
		try {
			bbsGroupInfo.setUserId(UserId);
			bbsGroupInfo.setGroupName(GroupName);
			bbsGroupInfo.setGroupIntroduce(GroupIntroduce);
			bbsGroupInfo.setGroupNumber(1);
			bbsGroupInfo.setCreateTime(CreateTime);
			bbsGroupInfo.setChangeTime(ChangeTime);

			em.persist(bbsGroupInfo);
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
	public List<BBSGroupInfo> getBBSGroup(String GroupName) {
		System.out.println("get bbsgroup from group name");

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select b from BBSGroupInfo b where b.GroupName = :GroupName");

		@SuppressWarnings("unchecked")
		List<BBSGroupInfo> bbsGroupInfos = (List<BBSGroupInfo>) q
				.getResultList();
		return bbsGroupInfos;
	}

	@Override
	public List<BBSGroupInfo> getBBSGroup() {
		System.out.println("get bbsgroup ");

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select b from BBSGroupInfo b");

		@SuppressWarnings("unchecked")
		List<BBSGroupInfo> bbsGroupInfos = (List<BBSGroupInfo>) q
				.getResultList();
		return bbsGroupInfos;
	}

}

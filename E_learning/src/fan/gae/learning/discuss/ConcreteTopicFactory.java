package fan.gae.learning.discuss;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fan.gae.learning.factory.TopicInfoFactory;
import fan.gae.learning.google.EMFService;

public class ConcreteTopicFactory extends TopicInfoFactory {

	@Override
	public boolean AddTopic(String GroupName, String UserId, String TopicTitle,
			String TopicContent, Date TopicLeastTime) {
		System.out.println("add topic ");
		boolean state = false;
		
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		TopicInfo topicInfo = new TopicInfo();
		txn.begin();
		try {
			topicInfo.setGroupName(GroupName);
			topicInfo.setUserId(UserId);
			topicInfo.setTopicTitle(TopicTitle);
			topicInfo.setTopicContent(TopicContent);
			topicInfo.setTopicLeaseTime(TopicLeastTime);
			topicInfo.setOwner(true);
			
			em.persist(topicInfo);
			txn.commit();
			state= true;
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
	public boolean AddReply(String GroupName, String UserId, String TopicTitle,
			String TopicContent, Date TopicLeastTime) {
		System.out.println("add reply ");
		boolean state =false;
		
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		TopicInfo topicInfo = new TopicInfo();
		txn.begin();
		try {
			topicInfo.setGroupName(GroupName);
			topicInfo.setUserId(UserId);
			topicInfo.setTopicTitle(TopicTitle);
			topicInfo.setTopicContent(TopicContent);
			topicInfo.setTopicLeaseTime(TopicLeastTime);
			topicInfo.setOwner(false);
			
			em.persist(topicInfo);
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
	public List<TopicInfo> getTopic(String GroupName, String UserId,
			String TopicTitle) {
		System.out.println("get topic from ");
		return null;
	}

	@Override
	public List<TopicInfo> getTopic(boolean Owner) {
		// TODO Auto-generated method stub
		return null;
	}

}

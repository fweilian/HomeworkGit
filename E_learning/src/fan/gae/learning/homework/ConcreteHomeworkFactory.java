package fan.gae.learning.homework;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.appengine.api.blobstore.BlobKey;

import fan.gae.learning.factory.HomeworkFactory;
import fan.gae.learning.google.EMFService;

public class ConcreteHomeworkFactory extends HomeworkFactory {

	@Override
	public boolean AddHomework(BlobKey blobKey, String teacherId,
			String courseName, String finishStyle, Date uploadTime,
			int downloadTimes) {
		System.out.println("add homework ");
		boolean state = false;
		
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		txn.begin();
		HomeworkInfo homeworkInfo = new HomeworkInfo();
		try {
			homeworkInfo.setBlobKey(blobKey);
			homeworkInfo.setTeacherId(teacherId);
			homeworkInfo.setCourseName(courseName);
			homeworkInfo.setFinishStyle(finishStyle);
			homeworkInfo.setUploadTime(uploadTime);
			homeworkInfo.setDownloadTimes(downloadTimes);
			
			em.persist(homeworkInfo);
			state = true;
			txn.commit();
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
	public List<HomeworkInfo> getHomework(String teacherId, String courseName) throws UnsupportedEncodingException {
		System.out.println("get homework info " +courseName +"  " + teacherId);
		
		EntityManager em=EMFService.get().createEntityManager();
		Query e = em.createQuery("select h from HomeworkInfo h");
		List<HomeworkInfo> homeworks1 = (List<HomeworkInfo>)e.getResultList();
		for(HomeworkInfo h:homeworks1) {
			System.out.println("fffff "+h.getCourseName() + "##qqqqq " + h.getTeacherId());
		}
		Query q = em.createQuery("select h from HomeworkInfo h where h.TeacherId = :teacherId");
		q.setParameter("teacherId", teacherId);
		//q.setParameter("courseName", courseName);
		@SuppressWarnings("unchecked")
		List<HomeworkInfo> homeworks = (List<HomeworkInfo>)q.getResultList();
		return homeworks;
	}

}

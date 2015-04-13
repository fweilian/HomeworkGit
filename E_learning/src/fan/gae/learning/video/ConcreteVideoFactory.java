package fan.gae.learning.video;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.appengine.api.blobstore.BlobKey;

import fan.gae.learning.factory.VideoFactory;
import fan.gae.learning.google.EMFService;

public class ConcreteVideoFactory extends VideoFactory {

	@Override
	public boolean AddVideo(BlobKey blobKey, String teacherId,
			String courseName, String courseTime, int viewTimes) {
		System.out.println("add video ");
		boolean state = false;

		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();

		txn.begin();
		VideoInfo videoInfo = new VideoInfo();
		try {
			videoInfo.setBlobKey(blobKey);
			videoInfo.setTeacherId(teacherId);
			videoInfo.setCourseName(courseName);
			videoInfo.setCourseTime(String.valueOf(Integer.parseInt(courseTime)-1));
			// videoInfo.setVideoType(videoType);
			videoInfo.setViewTimes(viewTimes);

			em.persist(videoInfo);
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
	public List<VideoInfo> getVideo(String teacherId) {
		System.out.println("get video " + teacherId);
		
		EntityManager em=EMFService.get().createEntityManager();
		Query q = em.createQuery("select v from VideoInfo v where v.TeacherId = :teacherId");
		q.setParameter("teacherId", teacherId);
		
		@SuppressWarnings("unchecked")
		List<VideoInfo> videoInfos = (List<VideoInfo>)q.getResultList();

		return videoInfos;
	}

}

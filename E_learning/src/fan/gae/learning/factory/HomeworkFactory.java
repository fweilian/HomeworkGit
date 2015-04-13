package fan.gae.learning.factory;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.blobstore.BlobKey;

import fan.gae.learning.homework.HomeworkInfo;

public abstract class HomeworkFactory {

	public abstract boolean AddHomework(BlobKey blobKey, String teacherId,
			String courseName, String finishStyle, Date uploadTime,
			int downloadTimes);
	
	public abstract List<HomeworkInfo> getHomework(String teacherId, String courseName) throws UnsupportedEncodingException;

	// public abstract
}

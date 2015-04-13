package fan.gae.learning.factory;

import java.util.List;

import com.google.appengine.api.blobstore.BlobKey;

import fan.gae.learning.video.VideoInfo;

public abstract class VideoFactory {

	public abstract boolean AddVideo(BlobKey blobKey, String teacherId,
			String courseName, String courseTime, int viewTimes);

	public abstract List<VideoInfo> getVideo(String teacherId);
}

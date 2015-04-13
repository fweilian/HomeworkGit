package fan.gae.learning.video;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.blobstore.BlobKey;

@Entity(name = "VideoInfo")
public class VideoInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long VideoId;

	private String CourseName;
	private String TeacherId;
	private String CourseTime;

	// private String VideoType;
	private int ViewTimes;

	@Basic
	private BlobKey blobKey;

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getTeacherId() {
		return TeacherId;
	}

	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}

	public String getCourseTime() {
		return CourseTime;
	}

	public void setCourseTime(String courseTime) {
		CourseTime = courseTime;
	}

	/*
	 * public String getVideoType() { return VideoType; }
	 * 
	 * public void setVideoType(String videoType) { VideoType = videoType; }
	 */

	public int getViewTimes() {
		return ViewTimes;
	}

	public void setViewTimes(int viewTimes) {
		ViewTimes = viewTimes;
	}

	public BlobKey getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(BlobKey blobKey) {
		this.blobKey = blobKey;
	}

	public Long getVideoId() {
		return VideoId;
	}

}

package fan.gae.learning.homework;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.blobstore.BlobKey;

@Entity(name = "HomeworkInfo")
public class HomeworkInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long HomeworkId;

	private String CourseName;
	private String TeacherId;

	private String FinishStyle;
	private Date UploadTime;
	private int DownloadTimes;
	@Basic
	private BlobKey blobKey;

	public BlobKey getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(BlobKey blobKey) {
		this.blobKey = blobKey;
	}

	public String getCourseName() throws UnsupportedEncodingException {
		
		return new String(CourseName.getBytes("iso-8859-1"),"utf-8");
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

	public String getFinishStyle() {
		return FinishStyle;
	}

	public void setFinishStyle(String finishStyle) {
		FinishStyle = finishStyle;
	}

	public Date getUploadTime() {
		return UploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		UploadTime = uploadTime;
	}

	public int getDownloadTimes() {
		return DownloadTimes;
	}

	public void setDownloadTimes(int downloadTimes) {
		DownloadTimes = downloadTimes;
	}

	public Long getHomeworkId() {
		return HomeworkId;
	}

}

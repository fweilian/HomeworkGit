package fan.gae.learning.course;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ChooseCourseInfo")
public class ChooseCourseInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ChooseId;

	private String UserId;
	private String CourseId;
	private Date StudyTime;
	private boolean state = false;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getCourseId() {
		return CourseId;
	}

	public void setCourseId(String courseId) {
		CourseId = courseId;
	}

	public Date getStudyTime() {
		return StudyTime;
	}

	public void setStudyTime(Date studyTime) {
		StudyTime = studyTime;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Long getChooseId() {
		return ChooseId;
	}

}

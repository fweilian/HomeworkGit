package fan.gae.learning.course;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity(name = "CourseInfo")
public class CourseInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CourseId;

	private String CourseName;
	
	private String CourseTime;
	private String[] CourseTitle;
	private String[] Catalogue;
	
	private String TeacherId;
	private String CourseType;
	private String CourseIntroduce;
	private int Like;
	private int UnLike;
	
	private boolean Pass=false;

	public boolean isPass() {
		return Pass;
	}

	public void setPass(boolean Pass) {
		this.Pass = Pass;
	}

	public Long getCourseId() {
		return CourseId;
	}

	/*
	 * public void setCourseId(Long courseId) { CourseId = courseId; }
	 */

	public String getCourseName() {
		return CourseName;
	}

	public String[] getCourseTitle() {
		return CourseTitle;
	}

	public void setCourseTitle(String[] courseTitle) {
		CourseTitle = courseTitle;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getCourseTime() {
		return CourseTime;
	}

	public void setCourseTime(String courseTime) {
		CourseTime = courseTime;
	}
	

	public String[] getCatalogue() {
		return Catalogue;
	}

	public void setCatalogue(String[] catalogue) {
		Catalogue = catalogue;
	}

	public String getTeacherId() {
		return TeacherId;
	}

	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}

	public String getCourseType() {
		return CourseType;
	}

	public void setCourseType(String courseType) {
		CourseType = courseType;
	}

	public String getCourseIntroduce() {
		return CourseIntroduce;
	}

	public void setCourseIntroduce(String courseIntroduce) {
		CourseIntroduce = courseIntroduce;
	}

	public int getLike() {
		return Like;
	}

	public void setLike(int like) {
		Like = like;
	}

	public int getUnLike() {
		return UnLike;
	}

	public void setUnLike(int unLike) {
		UnLike = unLike;
	}

}

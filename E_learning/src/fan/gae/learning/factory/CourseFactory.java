package fan.gae.learning.factory;

import java.util.List;

import fan.gae.learning.course.CourseInfo;

public abstract class CourseFactory {

	public abstract boolean AddCourse(String courseName, String courseTime,
			String[] catalogue, String teacherId, String courseType,
			String courseIntroduce, int like, int unLike);

	public abstract boolean ChangeCoursePass(CourseInfo courseInfo);

	public abstract boolean AddCourseCatalogue(CourseInfo courseInfo, String courseTitle,String courseIntroduce,String courseTime);
	
	//public abstract List<CourseInfo> getCourse(String courseId);

	public abstract List<CourseInfo> getCourse(String course,int flag);
	
	public abstract List<CourseInfo> getCourseFromType(String courseType);
	
	public abstract List<CourseInfo> getCourseFromPass(String Pass);

	public abstract boolean DeleteCourse(String courseName);
}

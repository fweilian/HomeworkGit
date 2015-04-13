package fan.gae.learning.factory;

import java.util.Date;
import java.util.List;

import fan.gae.learning.course.ChooseCourseInfo;

public abstract class ChooseCourseFactory {

	public abstract boolean AddChooseCourse(String userId, String courseId,
			Date studyTime, boolean state);

	public abstract boolean ChangeStudyTime(ChooseCourseInfo chooseCourseInfo,
			Date studyTime);

	public abstract boolean ChangeStudyState(ChooseCourseInfo chooseCourseInfo,
			boolean state);
	
	public abstract List<ChooseCourseInfo> getChooseCourse(String userId);
}

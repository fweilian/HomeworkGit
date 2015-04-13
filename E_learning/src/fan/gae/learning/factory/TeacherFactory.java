package fan.gae.learning.factory;

import java.util.List;

import fan.gae.learning.user.TeacherInfo;
import fan.gae.learning.user.UserInfo;

public abstract class TeacherFactory {

	public abstract boolean AddTeacher(UserInfo user, String realName,
			String oneIntroduce, String phoneNumber, String teachField,
			String teachPlan, String teachExperience);

	public abstract List<TeacherInfo> getTeacher(String userId);

	public abstract boolean ChangeTeacherInfo(TeacherInfo teacherInfo,
			String username, String password, String email, String phoneNumber,
			String teachField, String teachPlan, String teachExperience);

	public abstract List<TeacherInfo> TeacherInfo();
}

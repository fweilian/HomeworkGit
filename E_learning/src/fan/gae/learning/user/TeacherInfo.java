package fan.gae.learning.user;

import javax.persistence.Entity;

@Entity

public class TeacherInfo extends UserInfo {

	private String RealName;
	private String OneIntroduce;
	private String PhoneNumber;
	private String TeachField;
	private String TeachPlan;
	private String TeachExperience;
	
	
	public String getRealName() {
		return RealName;
	}
	public void setRealName(String realName) {
		RealName = realName;
	}
	public String getOneIntroduce() {
		return OneIntroduce;
	}
	public void setOneIntroduce(String oneIntroduce) {
		OneIntroduce = oneIntroduce;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getTeachField() {
		return TeachField;
	}
	public void setTeachField(String teachField) {
		TeachField = teachField;
	}
	public String getTeachPlan() {
		return TeachPlan;
	}
	public void setTeachPlan(String teachPlan) {
		TeachPlan = teachPlan;
	}
	public String getTeachExperience() {
		return TeachExperience;
	}
	public void setTeachExperience(String teachExperience) {
		TeachExperience = teachExperience;
	}
	
	
}

package fan.gae.learning.user;

import java.util.Date;

public interface UserInterface {

	//public abstract void setUserId(Long UserId);
	//public abstract Long getUserId();
	
	public abstract void setAutoId(String AutoId);
	public abstract String getAutoId();
	
	public abstract void setUserName(String UserName);
	public abstract String getUserName();
	
	public abstract void setPassword(String Password);
	public abstract String getPassword();
	
	public abstract void setRole(int Role); // visitor: 0 learner: 1 teacher: 2
											// administer: 3
	public abstract int getRole();
	
	public abstract void setEmail(String Email);
	public abstract String getEmail();
	
	public abstract void setRegisterTime(Date RegisterTime);
	public abstract Date getRegisterTime();
	
	public abstract void setVisitTimes(int VisitTimes);
	public abstract int getVisitTime();
}
